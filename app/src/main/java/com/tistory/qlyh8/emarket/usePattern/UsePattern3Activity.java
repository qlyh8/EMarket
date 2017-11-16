package com.tistory.qlyh8.emarket.usePattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tistory.qlyh8.emarket.dataManager.UseMonthExcelFile;
import com.tistory.qlyh8.emarket.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class UsePattern3Activity extends Fragment {

    View view;
    private String patternType;

    UseMonthExcelFile um;
    HashMap<String, String> hashMap;

    public final static String[] fields = new String[3];             //칼럼에 들어갈 숫자(1..3)
    public final static int[] fieldNumbers = new int[3];             //칼럼에 들어갈 월별 사용량

    int numSubcolumns = 1;
    int numColumns = fields.length;

   private ColumnChartView columnChart;
    private ColumnChartData columnData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        patternType = getArguments().getString("patternType");
        view = inflater.inflate(R.layout.use_pattern_item3, container, false);

        um = new UseMonthExcelFile(getContext());

        columnChart = view.findViewById(R.id.use_pattern_month_container);
        hashMap = um.selectByYear(2017);

        //칼럼에 들어갈 Field 데이터 세팅
        for(int i = 0, j = 1 ; i < 3 ; i++, j++){
            fields[i] = "2017." + j;
            //fieldNumbers[i] = (int) hashMap.get("UM_USE" + j);
            fieldNumbers[i] = Integer.parseInt(hashMap.get("UM_USE" + j));
        }
        Toast.makeText(getContext(), "사용량1: "+hashMap.get("UM_USE1")+ "\n사용량2: " + hashMap.get("UM_USE2")+ "\n사용량3: "+hashMap.get("UM_USE3"), Toast.LENGTH_LONG).show();

        generateColumnData();
        prepareDataAnimation();
        columnChart.startDataAnimation(1000);

        return view;
    }

    public void generateColumnData() {

        List<AxisValue> axisValues = new ArrayList<>();
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;

        for (int i = 0, j = 1; i < numColumns; ++i, ++j) {
            values = new ArrayList<>();
            for (int k = 0; k < numSubcolumns; ++k) {
                //values.add(new SubcolumnValue((int)fieldNumbers[i], ChartUtils.pickColor()));
                values.add(new SubcolumnValue(fieldNumbers[k], ChartUtils.pickColor()));        //여기
            }

            axisValues.add(new AxisValue(i).setLabel(fields[i]));
            columns.add(new Column(values).setHasLabelsOnlyForSelected(true));

        }

        columnData = new ColumnChartData(columns);

        columnData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        columnData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(4));

        columnChart.setColumnChartData(columnData);

        // Set selection mode to keep selected bottom column highlighted.
        columnChart.setValueSelectionEnabled(true);
    }

    private void prepareDataAnimation() {

        List<Column> tempColumns = columnData.getColumns();
        for (int i = 0 ; i < numColumns ; ++i) {
            List<SubcolumnValue> tempSubs = tempColumns.get(i).getValues();
            for (int j = 0; j < numSubcolumns ; ++j) {
                tempSubs.get(j).setTarget(fieldNumbers[i]);
            }
        }
    }
}
