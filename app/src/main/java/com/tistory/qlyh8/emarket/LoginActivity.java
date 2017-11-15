package com.tistory.qlyh8.emarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tistory.qlyh8.emarket.firebase.GetAuth;
import com.tistory.qlyh8.emarket.firebase.GetDB;
import com.tistory.qlyh8.emarket.initialize.InitialPowerNumberActivity;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private LoadingActivity loading;
    private Button loginBtn, nextBtn;
    private EditText phoneNumber;
    private EditText authNumber;
    private boolean isComplete = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        loading = new LoadingActivity(this);

        //로그인 체크
        if(mAuth.getCurrentUser() != null) {
            //다른 액티비티로 넘어가기
            //Toast.makeText(this, mAuth.getCurrentUser().getPhoneNumber(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "로그인되었습니다.", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(this, MainActivity.class));
            //finish();
        }

        loginBtn = (Button)findViewById(R.id.login_send_auth_btn);
        phoneNumber = (EditText) findViewById(R.id.login_phone_number);
        phoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        authNumber = (EditText)findViewById(R.id.login_get_auth_number);
        nextBtn = (Button)findViewById(R.id.login_next_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //예외처리
                if(phoneNumber.getText().toString() == null || phoneNumber.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                startPhoneNumberVerification(phoneNumber.getText().toString());
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isComplete){
                    startActivity(new Intent(getBaseContext(), InitialPowerNumberActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "본인인증이 필요합니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void startPhoneNumberVerification(String phoneNumber) {
        loading.show(); //로딩 시작

        PhoneAuthProvider.OnVerificationStateChangedCallbacks  mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                signInWithPhoneAuthCredential(credential);
            }
            @Override
            public void onVerificationFailed(FirebaseException e) {
                //인증 실패시
                Toast.makeText(LoginActivity.this, "인증이 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }};

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                20,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }

    private void signInWithPhoneAuthCredential(final PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loading.dismiss(); //로딩 끝

                        if (task.isSuccessful()) {
                            //FirebaseUser user = task.getResult().getUser();
                            authNumber.setText(credential.getSmsCode());
                            //인증 성공 유저 정보 읽어오고 인증번호에 값 할당하는곳
                            userRegister();
                            isComplete = true;
                            Toast.makeText(LoginActivity.this, "인증이 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "인증에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void userRegister(){
        GetDB.mUserRef.addValueEventListener(new ValueEventListener() {
            boolean isUser = false;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    if(snapshot.getKey().equals(GetAuth.getUserId())) {
                        isUser = true;
                        break;
                    }
                }
                if(!isUser){
                    GetDB.mUserRef.child(GetAuth.getUserId()).child("phone").setValue(GetAuth.getUserPhone());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }
}

/*public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;

    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference();
    private DatabaseReference mUserRef = mDatabaseReference.child("user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activiy_login);
        loginInit();
    }

    private void loginInit(){

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)//onConnectionFailed Listener
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                Toast.makeText(getApplicationContext(), "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        task.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

                        if (task.isSuccessful()) {
                            userRegister();
                        } else {
                            Toast.makeText(getApplicationContext(), "네트워크 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }

    public void googleSignIn(View v){
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, RC_SIGN_IN);
            Toast.makeText(getApplicationContext(), "잠시만 기다려주세요", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void userRegister(){

        final String userName = mFirebaseAuth.getCurrentUser().getDisplayName();
        final String uId = mFirebaseAuth.getCurrentUser().getUid();

        mUserRef.addValueEventListener(new ValueEventListener() {
            boolean isUser = false;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    if(snapshot.getKey().equals(uId)) {
                        isUser = true;
                        break;
                    }
                    //System.out.println(snapshot.child("title").getValue(String.class));
                }

                if(!isUser){
                    mDatabaseReference.child("user").child(uId).child("username").setValue(userName);

                    Toast.makeText(getApplicationContext(), "안녕하세요 " + userName + "님!", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(getBaseContext(), InfoActivity.class));
                    //finish();
                }
                else{
                    //startActivity(new Intent(getBaseContext(), MainActivity.class));
                    //finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        startActivity(new Intent(getBaseContext(), InfoActivity.class));
        finish();
    }
}*/
