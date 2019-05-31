package com.lin.person.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lin.baselib.arouter.ArouterHelper;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.baselib.db.SPManager;
import com.lin.baselib.entity.User;
import com.lin.baselib.util.ImageLoaderUtil;
import com.lin.person.R;
import com.lin.person.entity.UserDTO;
import com.lin.person.presenter.InfoManagePresenter;
import com.lin.person.utils.PhotoUtil;
import com.lin.person.view.InfoManageView;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by lin on 2019/5/10.
 */

@RuntimePermissions
@Route(path = ArouterPath.ACTIVITY_INFOMANAGE)
public class InfoManageActivity extends BaseActivity<InfoManagePresenter> implements InfoManageView{
    private static final int TAKE_PHOTO = 0;
    private static final int CHOOSE_PHOTO = 1;
    private static final int CROP_PICTURE = 2;
    private TextView tvSave;
    private CircleImageView civUserPic;
    private EditText etUsername;
    private EditText etGender;
    private RelativeLayout rlInfoAddress;
    @Override
    protected InfoManagePresenter createPresenter() {
        return new InfoManagePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.person_activity_info_manage;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        etUsername = findViewById(R.id.et_username);
        etGender = findViewById(R.id.et_gender);
        etUsername.setText(User.getUser().getUsername());
        etGender.setText(User.getUser().getGender()?"男":"女");
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvSave = findViewById(R.id.tv_save);
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(etGender.getText().toString().trim().equals("男")||etGender.getText().toString().trim().equals("女")){
                     presenter.save(new UserDTO(User.getUser().getUserId(),etUsername.getText().toString(),etGender.getText().equals("男"),avatarTemp));
                 }else{
                     showToast("性别只能是男或女");
                 }
            }
        });
        civUserPic = findViewById(R.id.civ_user_pic);
        ImageLoaderUtil.getInstance(getApplicationContext()).displayImage(civUserPic, User.getUser().getAvatar());
        civUserPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShareDialog();
            }
        });
        rlInfoAddress = findViewById(R.id.rl_info_address);
        rlInfoAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArouterHelper.gotoAddressActivity();
            }
        });
    }
    private PopupWindow mpopupWindow;
    private void showShareDialog() {


        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.person_upload_user_logo_popwindow, null);

        // 监听
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btn_pick_photo){
                    chooseImageFromCameraAlbum();
                    mpopupWindow.dismiss();
                }else if(v.getId()==R.id.btn_take_photo){
                    InfoManageActivityPermissionsDispatcher.getCameraPermissionWithPermissionCheck(InfoManageActivity.this);

                }else if(v.getId()== R.id.btn_photo_cancel){
                    mpopupWindow.dismiss();
                }
            }

        };
        Button pick =  (Button) view.findViewById(R.id.btn_pick_photo);
        Button take = (Button) view.findViewById(R.id.btn_take_photo);
        Button cancel = (Button) view.findViewById(R.id.btn_photo_cancel);
        pick.setOnClickListener(listener);
        take.setOnClickListener(listener);
        cancel.setOnClickListener(listener);

        if(mpopupWindow==null){
            mpopupWindow = new PopupWindow(this);
            mpopupWindow.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
            mpopupWindow.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
            mpopupWindow.setFocusable(true);
            mpopupWindow.setOutsideTouchable(true);
            mpopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    backgroundAlpha(1.0f);
                }

            });
        }

        mpopupWindow.setContentView(view);
        mpopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        mpopupWindow.setAnimationStyle(R.style.popwin_anim_style);
        if(Build.VERSION.SDK_INT < 24) {
            //版本7.0以下
            mpopupWindow.showAtLocation(findViewById(R.id.civ_user_pic), Gravity.BOTTOM, 0, 0);
        }else {
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int mWidthPixels = dm.widthPixels;
            int mHeightPixels = dm.heightPixels;
            mpopupWindow.getContentView().measure(0,0);
            int popHeight = mpopupWindow.getContentView().getMeasuredHeight();
            mpopupWindow.showAtLocation(findViewById(R.id.content_view), Gravity.LEFT, 0, mHeightPixels - popHeight);
        }
        backgroundAlpha(0.6f);
        mpopupWindow.update();

    }
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        getWindow().setAttributes(lp);
    }
    private void chooseImageFromCameraAlbum() {
        Log.i("openAlbum","true");
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    void getCameraPermission() {
        getImageFromCamera();
        mpopupWindow.dismiss();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        InfoManageActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private Uri imageUri;
    private void handleImageBeforeKitKat(Intent data) {
        Log.i("BeforeKitKat","use");
        imageUri = data.getData();
        PhotoUtil.cropImageUri(this,imageUri,400,400,CROP_PICTURE);
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        // Toast.makeText(getApplicationContext(), "KitKat", Toast.LENGTH_SHORT).show();
        Log.i("OmKitKat","use");
        String imagePath = null;
        Uri uri =data.getData();
        //imageUri = uri;
        if(DocumentsContract.isDocumentUri(getApplicationContext(),uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID+"="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        //imageUri =  Uri.parse(imagePath);
        // imageView.setImageBitmap(bitmap);
        imageUri= PhotoUtil.bitmap2Uri(getApplicationContext(), System.currentTimeMillis()+".png",bitmap);
        PhotoUtil.cropImageUri(this,imageUri,400,400,CROP_PICTURE);
    }
    private void getImageFromCamera() {
        File cameraImage= new File(getExternalCacheDir(),"camera_image.png");
        if(cameraImage.exists()){
            cameraImage.delete();
        }
        try {
            cameraImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Build.VERSION.SDK_INT>=24){
            imageUri = FileProvider.getUriForFile(getApplication(),"com.lin.person.fileprovider",cameraImage);
        }else{
            imageUri = Uri.fromFile(cameraImage);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,TAKE_PHOTO);

    }
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    PhotoUtil.cropImageUri(this,imageUri, 400, 400, CROP_PICTURE);
                }
                break;
            case CHOOSE_PHOTO:
                if(resultCode==RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            case CROP_PICTURE:
                if(resultCode==RESULT_OK){
                    if(imageUri != null){
                        String path = imageUri.getPath();
                        Bitmap bm = PhotoUtil.decodeUriAsBitmap(InfoManageActivity.this, imageUri);
                        civUserPic.setImageBitmap(bm);
                        File file = new File(path);
                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
                        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                        presenter.changeAvatar(User.getUser().getUserId(),body);
                    }
                }
                break;
            default:
                break;
        }
    }
    private String avatarTemp = User.getUser().getAvatar();
    @Override
    public void saveAvatar(String data) {
        avatarTemp = data;
    }

    @Override
    public void updateUser() {
        User.getUser().setGender(etGender.getText().toString().trim().equals("男"));
        User.getUser().setUsername(etUsername.getText().toString().trim());
        User.getUser().setAvatar(avatarTemp);
        SPManager.getInstance().putBoolean("gender", User.getUser().getGender());
        SPManager.getInstance().putString("username", User.getUser().getUsername());
        SPManager.getInstance().putString("avatar", User.getUser().getAvatar());
    }
}
