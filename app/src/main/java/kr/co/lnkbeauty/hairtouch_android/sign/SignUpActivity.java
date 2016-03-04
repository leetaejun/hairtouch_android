package kr.co.lnkbeauty.hairtouch_android.sign;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.lnkbeauty.hairtouch_android.R;
import kr.co.lnkbeauty.hairtouch_android.model.AuthenticationModel;
import kr.co.lnkbeauty.hairtouch_android.model.DesignerModel;
import kr.co.lnkbeauty.hairtouch_android.network.APIRequest;
import kr.co.lnkbeauty.hairtouch_android.network.APIService;
import kr.co.lnkbeauty.hairtouch_android.util.PreferencesManager;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SignUpActivity extends AppCompatActivity {

    @Bind(R.id.activity_sign_up_toolbar_include)
    View toolbarView;

    @Bind(R.id.activity_sign_up_email_et)
    EditText emailEditText;
    @Bind(R.id.activity_sign_up_password_et)
    EditText passwordEditText;
    @Bind(R.id.activity_sign_up_nickname_et)
    EditText nicknameEditText;

    private boolean hasEmail;
    private boolean hasPassword;
    private boolean hasNickname;

    @Bind(R.id.activity_sign_up_sign_up_btn)
    Button signupButton;

    // VAR
    private DesignerModel designerModel;
    private AuthenticationModel authenticationModel;

    // Dialog
    private ProgressDialog progressDialog;

    @OnClick(R.id.activity_sign_up_sign_up_btn)
    public void onClick() {

        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setMessage("회원가입 중입니다.");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(true);
        progressDialog.show();

        designerModel.setEmail(emailEditText.getText().toString());
        designerModel.setPassword(passwordEditText.getText().toString());
        designerModel.setNickname(nicknameEditText.getText().toString());

        APIService service = APIRequest.getInstacne().getService();
        service.signupDesigner(designerModel).enqueue(new Callback<AuthenticationModel>() {
            @Override
            public void onResponse(Response<AuthenticationModel> response, Retrofit retrofit) {
                if (response.body() != null) {
                    authenticationModel = response.body();

                    PreferencesManager.getInstance().setAccessToken(authenticationModel.getAccess_token());
                    PreferencesManager.getInstance().setDesigner(authenticationModel.getDesigner());

                    progressDialog.dismiss();
                } else {
                    // .. error handling
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.start_top_bottom_enter, R.anim.start_top_bottom_exit);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        designerModel = new DesignerModel();

        initializeToolBar();
        initializeFlag();
        addTextChangedListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.end_top_bottom_enter, R.anim.end_top_bottom_exit);
    }

    private void initializeToolBar() {
        ((TextView)toolbarView.findViewById(R.id.layout_toolbar_close_title_tv)).setText("SIGN UP");
        ((RelativeLayout)toolbarView.findViewById(R.id.layout_toolbar_close_rl)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initializeFlag() {
        hasEmail = false;
        hasPassword = false;
        hasNickname = false;

    }

    private void addTextChangedListener() {
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    hasEmail = true;
                } else {
                    hasEmail = false;
                }

                setSignUpButtonStatus(hasEmail, hasPassword, hasNickname);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 1) {
                    hasPassword = true;
                } else {
                    hasPassword = false;
                }

                setSignUpButtonStatus(hasEmail, hasPassword, hasNickname);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        nicknameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 1) {
                    hasNickname = true;
                } else {
                    hasNickname = false;
                }

                setSignUpButtonStatus(hasEmail, hasPassword, hasNickname);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setSignUpButtonStatus(boolean hasEmail, boolean hasPassword, boolean hasFullname) {
        if (hasEmail && hasPassword && hasFullname) {
            signupButton.setEnabled(true);
        } else {
            signupButton.setEnabled(false);
        }
    }

}
