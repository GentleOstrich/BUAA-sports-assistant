package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Bean.*;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.util.ViewUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@SuppressLint("DefaultLocale")
public class LoginSQLiteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_account; // 声明一个编辑框对象
    private TextView tv_password; // 声明一个文本视图对象
    private EditText et_password; // 声明一个编辑框对象
    //    private Button btn_forget; // 声明一个按钮控件对象
    private CheckBox ck_remember; // 声明一个复选框对象

    private int mRequestCode = 0; // 跳转页面时的请求代码
    private boolean isRegister = false; // 是否注册
    //    private String mPassword = "111111"; // 默认密码
    //    private UserDBHelper mHelper; // 声明一个用户数据库的帮助器对象
//    private MdbHelper mdbHelper;
    private UserDao userDao = null;
//    private TeamDao teamDao = null;
//    private Team2UserDao team2UserDao = null;
//    private SportsDao sportsDao = null;
//    private StrategyDao strategyDao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sqlite);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //修改actionbar左上角返回按钮的图标
        et_account = findViewById(R.id.et_account);
        tv_password = findViewById(R.id.tv_password);
        et_password = findViewById(R.id.et_password);
//        btn_forget = findViewById(R.id.btn_forget);
        ck_remember = findViewById(R.id.ck_remember);
        // 给ck_remember设置勾选监听器
        ck_remember.setOnCheckedChangeListener((buttonView, isChecked) -> isRegister = isChecked);
        // 给et_phone添加文本变更监听器
        et_account.addTextChangedListener(new HideTextWatcher(et_account, 15));
        // 给et_password添加文本变更监听器
        et_password.addTextChangedListener(new HideTextWatcher(et_password, 20));
//        btn_forget.setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
        // 给密码编辑框注册一个焦点变化监听器，一旦焦点发生变化，就触发监听器的onFocusChange方法
//        et_password.setOnFocusChangeListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:   //返回键的id
                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // 定义登录方式的单选监听器
    private class RadioListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            tv_password.setText("登录密码：");
            et_password.setHint("请输入密码");
//            btn_forget.setText("忘记密码");
            ck_remember.setVisibility(View.VISIBLE);
        }
    }

    // 定义一个编辑框监听器，在输入文本达到指定长度时自动隐藏输入法
    private class HideTextWatcher implements TextWatcher {
        private EditText mView; // 声明一个编辑框对象
        private int mMaxLength; // 声明一个最大长度变量

        public HideTextWatcher(EditText v, int maxLength) {
            super();
            mView = v;
            mMaxLength = maxLength;
        }

        // 在编辑框的输入文本变化前触发
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        // 在编辑框的输入文本变化时触发
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        // 在编辑框的输入文本变化后触发
        public void afterTextChanged(Editable s) {
            String str = s.toString(); // 获得已输入的文本字符串
            // 输入文本达到11位（如手机号码），或者达到6位（如登录密码）时关闭输入法
            if ((str.length() == 11 && mMaxLength == 11) || (str.length() == 6 && mMaxLength == 6)) {
                ViewUtil.hideOneInputMethod(LoginSQLiteActivity.this, mView); // 隐藏输入法软键盘
            }
        }
    }

    @Override
    public void onClick(View v) {
        String account = et_account.getText().toString();
        String pwd = et_password.getText().toString();
//        if (v.getId() == R.id.btn_forget) { // 点击了“忘记密码”按钮
//            if (account.length() < 5) { // 账号不足5位
//                Toast.makeText(this, "账号要长度大于5位且小于15位", Toast.LENGTH_SHORT).show();
//            }
//        } else
        if (v.getId() == R.id.btn_login) { // 点击了“登录”按钮
            if (account.length() < 5) { // 账号不足5位
                Toast.makeText(this, "账号要长度大于5位且小于15位", Toast.LENGTH_SHORT).show();
                return;
            }
            List<UserBean> userBeans = userDao.queryByAccount(account);
            if (userBeans.isEmpty()) {
                if (isRegister) {
                    // 进行注册
                    UserBean userBean = new UserBean(et_account.getText().toString(), et_password.getText().toString());
                    userDao.insert(userBean);
                    try {
                        loginSuccess(); // 提示用户登录成功
                        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("account", account);
                        editor.apply();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Toast.makeText(this, "用户不存在，请先注册", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (isRegister) {
                    Toast.makeText(this, "用户已存在，请勿重复注册", Toast.LENGTH_SHORT).show();
                } else {
                    for (UserBean userBean : userBeans) {
                        if (Objects.equals(userBean.getPassword(), pwd)) {
                            // 密码校验通过
                            try {
                                loginSuccess(); // 提示用户登录成功
                                SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("account", account);
                                editor.apply();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            return;
                        }
                    }
                    Toast.makeText(this, "请输入正确密码", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // 从下一个页面携带参数返回当前页面时触发
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mRequestCode && data != null) {
            // 用户密码已改为新密码，故更新密码变量
//            mPassword = data.getStringExtra("new_password");
        }
    }

    // 从修改密码页面返回登录页面，要清空密码的输入框
    @Override
    protected void onRestart() {
        super.onRestart();
        et_password.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mHelper = UserDBHelper.getInstance(this, 1); // 获得用户数据库帮助器的实例
//        mHelper.openWriteLink(); // 恢复页面，则打开数据库连接
//        mdbHelper = MdbHelper.getInstance(this); // 获得用户数据库帮助器的实例
        userDao = new UserDao(this);
//        teamDao = new TeamDao(this);
//        team2UserDao = new Team2UserDao(this);
//        sportsDao = new SportsDao(this);
//        strategyDao = new StrategyDao(this);
//        mdbHelper.openWriteLink(); // 恢复页面，则打开数据库连接
    }

    @Override
    protected void onPause() {
        super.onPause();
        userDao = null;
//        teamDao = null;
//        team2UserDao = null;
//        sportsDao = null;
//        strategyDao = null;
//        mdbHelper.closeLink(); // 暂停页面，则关闭数据库连接
//        mdbHelper.closeLink(); // 暂停页面，则关闭数据库连接
    }

    // 校验通过，登录成功
    private void loginSuccess() throws SQLException {
        String desc = String.format("尊敬的用户：%s，欢迎您", et_account.getText().toString());
        // 以下弹出提醒对话框，提示用户登录成功
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录成功");
        builder.setMessage(desc);
        builder.setPositiveButton("确定", (dialog, which) -> finish());
//        builder.setNegativeButton("我再看看", null);
        AlertDialog alert = builder.create();
        alert.show();
        // 如果勾选了“记住密码”，则把手机号码和密码保存为数据库的用户表记录
    }

    // 焦点变更事件的处理方法，hasFocus表示当前控件是否获得焦点。
    // 为什么光标进入密码框事件不选onClick？因为要点两下才会触发onClick动作（第一下是切换焦点动作）
//    @Override
//    public void onFocusChange(View v, boolean hasFocus) {
//        String phone = et_phone.getText().toString();
//        // 判断是否是密码编辑框发生焦点变化
//        if (v.getId() == R.id.et_password) {
//            // 用户已输入手机号码，且密码框获得焦点
//            if (phone.length() > 0 && hasFocus) {
//                ArrayList<UserBean> userBean = new ArrayList<>();
//                userBean.addAll(userDao.queryByAccount(phone));
//                if (!userBean.isEmpty()) {
//                    // 找到用户记录，则自动在密码框中填写该用户的密码
////                    et_password.setText(userBean.get(0).getPassword());
//                }
//                // 根据手机号码到数据库中查询用户记录
////                UserInfo info = mHelper.queryByPhone(phone);
////                if (info != null) {
////                    // 找到用户记录，则自动在密码框中填写该用户的密码
////                    et_password.setText(info.password);
////                }
//            }
//        }
//    }

}
