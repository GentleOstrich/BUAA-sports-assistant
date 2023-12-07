package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class PlanActivity extends AppCompatActivity {

    private TextView weekday;

    private TextView plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        weekday = findViewById(R.id.plan_weekday);
        plan = findViewById(R.id.plan_plan);

        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 将日历中的星期几转换为字符串
        getDayOfWeekString(dayOfWeek);


    }

    private void getDayOfWeekString(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                weekday.setText("星期天");
                plan.setText("休息日\n");
                return;
            case Calendar.MONDAY:
                weekday.setText("星期一");
                plan.setText("胸部:\n \t仰卧推举（4组，分别为12、10、8、6次反复，练到力竭）\n \t上斜推举（4组，分别为12、10、8、6次反复，练到力竭）\n \t仰卧上拉（4组，分别为12、10、8、6次反复，练到力竭）\n 背部:\n \t引体向上（4组，分别为12、10、8、6次反复，练到力竭）\n \t俯身划船（4组，分别为12、10、8、6次反复，练到力竭）\n \t硬拉（3组，分别为10、6、4次反复，练到力竭）\n 腹部:\n \t卷腹（5组，每组25次反复）\n");
                return;
            case Calendar.TUESDAY:
                weekday.setText("星期二");
                plan.setText("肩部:\n \t提铃上举（4组，分别为12、10、8、6次反复，练到力竭）\n \t哑铃侧平举（4组，分别为12、10、8、6次反复，练到力竭）\n \t直立划船（3组，分别为10、6、4次反复，练到力竭）\n \t借力推举（3组，分别为10、6、4次反复，练到力竭）\n 上臂:\n \t站姿杠铃弯举（4组，分别为12、10、8、6次反复，练到力竭）\n \t坐姿哑铃弯举（4组，分别为12、10、8、6次反复，练到力竭）\n \t窄握推举（4组，分别为12、10、8、6次反复，练到力竭）\n \t站姿杠铃臂屈伸（4组，分别为12、10、8、6次反复，练到力竭）\n 前臂:\n \t腕弯举（4组，分别为12、10、8、6次反复，练到力竭）\n \t反握腕弯举（4组，分别为12、10、8、6次反复，练到力竭）\n 腹部:\n \t反向卷腹（5组，每组25次反复）\n");
                return;
            case Calendar.WEDNESDAY:
                weekday.setText("星期三");
                plan.setText("大腿:\n \t深蹲（4组，分别为15、12、10、8次反复，练到力竭）\n \t弓步（4组，分别为15、12、10、8次反复，练到力竭）\n \t腿弯举（4组，分别为15、12、10、8次反复，练到力竭）\n 小腿:\n \t站姿提踵（5组，每组15次反复）\n \t下背直腿硬拉（3组，分别为10、6、4次反复，练到力竭）\n \t负重体前屈（3组，分别为10、6、4次反复，练到力竭）\n 腹部:\n \t卷腹（5组，每组25次反复）\n");
                return;
            case Calendar.THURSDAY:
                weekday.setText("星期四");
                plan.setText("胸部:\n \t仰卧推举（4组，分别为12、10、8、6次反复，练到力竭）\n \t上斜推举（4组，分别为12、10、8、6次反复，练到力竭）\n \t仰卧上拉（4组，分别为12、10、8、6次反复，练到力竭）\n 背部:\n \t引体向上（4组，分别为12、10、8、6次反复，练到力竭）\n \t俯身划船（4组，分别为12、10、8、6次反复，练到力竭）\n \t硬拉（3组，分别为10、6、4次反复，练到力竭）\n 腹部:\n \t卷腹（5组，每组25次反复）\n");
                return;
            case Calendar.FRIDAY:
                weekday.setText("星期五");
                plan.setText("肩部:\n \t提铃上举（4组，分别为12、10、8、6次反复，练到力竭）\n \t哑铃侧平举（4组，分别为12、10、8、6次反复，练到力竭）\n \t直立划船（3组，分别为10、6、4次反复，练到力竭）\n \t借力推举（3组，分别为10、6、4次反复，练到力竭）\n 上臂:\n \t站姿杠铃弯举（4组，分别为12、10、8、6次反复，练到力竭）\n \t坐姿哑铃弯举（4组，分别为12、10、8、6次反复，练到力竭）\n \t窄握推举（4组，分别为12、10、8、6次反复，练到力竭）\n \t站姿杠铃臂屈伸（4组，分别为12、10、8、6次反复，练到力竭）\n 前臂:\n \t腕弯举（4组，分别为12、10、8、6次反复，练到力竭）\n \t反握腕弯举（4组，分别为12、10、8、6次反复，练到力竭）\n 腹部:\n \t反向卷腹（5组，每组25次反复）\n");
                return;
            case Calendar.SATURDAY:
                weekday.setText("星期六");
                plan.setText("大腿:\n \t深蹲（4组，分别为15、12、10、8次反复，练到力竭）\n \t弓步（4组，分别为15、12、10、8次反复，练到力竭）\n \t腿弯举（4组，分别为15、12、10、8次反复，练到力竭）\n 小腿:\n \t站姿提踵（5组，每组15次反复）\n \t下背直腿硬拉（3组，分别为10、6、4次反复，练到力竭）\n \t负重体前屈（3组，分别为10、6、4次反复，练到力竭）\n 腹部:\n \t卷腹（5组，每组25次反复）\n");
                return;
            default:
                return;
        }
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
}