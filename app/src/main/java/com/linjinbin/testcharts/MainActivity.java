package com.linjinbin.testcharts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity {

    private LineChartView mLineChartView;

    String[] date = {"10-22","11-22","12-22","1-22","6-22","5-23","5-22","6-22","5-23","5-22"};//X轴的标注
    int[] score= {50,42,90,33,10,74,22,18,79,20};//图表的数据点
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisValues = new ArrayList<AxisValue>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLineChartView = (LineChartView) findViewById(R.id.main_chart);

        for (int i = 0; i < 100 ; i++) {
            mPointValues.add(new PointValue(i, new Random().nextInt(10)));
//            mAxisValues.add(new AxisValue(i).setLabel(i+"")); //为每个对应的i设置相应的label(显示在X轴)
        }
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41")).setCubic(true);
        line.setShape(ValueShape.CIRCLE);
        line.setHasLabels(true);
//        line.setHasLabelsOnlyForSelected(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        List<AxisValue> values = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AxisValue value = new AxisValue(i);
            value.setLabel(i+"");
            values.add(value);
        }
        AxisValue v2 = new AxisValue(55);
        v2.setLabel(55 + "");
        values.add(v2);


        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);
        axisX.setName("采集时间");
        axisX.setMaxLabelChars(8);
        axisX.setValues(values);

        data.setAxisXBottom(axisX);


        Viewport v = new Viewport(mLineChartView.getMaximumViewport());
        v.left = 0;
        v.right= 7;
        mLineChartView.setCurrentViewport(v);



        Axis axisY = new Axis();  //Y轴
        axisY.setMaxLabelChars(7); //默认是3，只能看最后三个数字
        data.setAxisYLeft(axisY);

        //设置行为属性，支持缩放、滑动以及平移
        mLineChartView.setInteractive(true);
        mLineChartView.setZoomType(ZoomType.HORIZONTAL);
        mLineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        mLineChartView.setLineChartData(data);
        mLineChartView.setVisibility(View.VISIBLE);



//
//        getAxisXLables();//获取x轴的标注
//        getAxisPoints();//获取坐标点
//        initLineChart();//初始化

//        Axis axisY = new Axis().setHasLines(true);
//        axisY.setMaxLabelChars(6);//max label length, for example 60
//        List<AxisValue> values = new ArrayList<>();
//        for(int i = 0; i < 100; i+= 10){
//            AxisValue value = new AxisValue(i);
//            String label = "";
//            value.setLabel(label);
//            values.add(value);
//        }
//        axisY.setValues(values);

    }

    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables(){
        for (int i = 0; i < date.length; i++) {
//            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }
    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints() {
        for (int i = 0; i < score.length; i++) {
            mPointValues.add(new PointValue(i, score[i]));
        }
    }
    private void initLineChart(){
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.WHITE);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
//        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边

//
//        //设置行为属性，支持缩放、滑动以及平移
//        lineChart.setInteractive(true);
//        lineChart.setZoomType(ZoomType.HORIZONTAL);
//        lineChart.setMaxZoom((float) 2);//最大方法比例
//        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
//        lineChart.setLineChartData(data);
//        lineChart.setVisibility(View.VISIBLE);
//        /**注：下面的7，10只是代表一个数字去类比而已
//         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
//         */
//        Viewport v = new Viewport(lineChart.getMaximumViewport());
//        v.left = 0;
//        v.right= 7;
//        lineChart.setCurrentViewport(v);
    }


}

