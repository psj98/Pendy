import React, { useEffect, useState } from 'react';
import './UserTemplate.css';
import { addMonths, subMonths } from 'date-fns';
import CalenderHeader from '../../components/main/calender-header/CalenderHeader';
import CalenderDays from '../../components/main/calender-days/CalenderDays';
import CalenderCells from '../../components/main/calender-cells/CalenderCells';
import DonutChart from '../../components/common/donut-chart/DonutChart';
import GoalBar from '../../components/common/goal-bar/GoalBar';
import DayMonthButton from '../../components/main/day-month-button/DayMonthButton';
import handleCalender from '../../utils/handleCalender';
import { format } from 'date-fns';

//유저 전용 메인 페이지
const UserTemplate = () => {
  const [currentMonth, setCurrentMonth] = useState(new Date());
  const [responseData, setResponseData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        // yyyy-MM-ddTHH:mm:ss.SSSZ 형식으로 todayDate 값 생성
        // yyyy-MM-ddTHH:mm:ss.SSS+09:00 형식으로 todayDate 값 생성
        const todayDate = format(
          currentMonth,
          "yyyy-MM-dd'T'HH:mm:ss.SSS'+09:00'",
        );
        // yyyy-MM-01T00:00:00.000+09:00 형식으로 todayMonth 값 생성
        const todayMonth = format(
          currentMonth,
          "yyyy-MM-dd'T'HH:mm:ss.SSS'+09:00'",
        );
        const response = await handleCalender(todayDate, todayMonth);
        console.log(response.data);
        setResponseData(response.data); // response 데이터를 상태로 저장
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, [currentMonth]);
  console.log('usertemplate ' + currentMonth);

  // 달력을 이전달로 넘기는 기능
  const prevMonth = () => {
    setCurrentMonth(subMonths(currentMonth, 1));
  };

  //달력을 다음 달로 넘기는 기능
  const nextMonth = () => {
    setCurrentMonth(addMonths(currentMonth, 1));
  };

  // console.log('응답데이터입니다!!!! : ', responseData);
  // console.log(
  //   '응답데이터입니다!!!! : ',
  //   responseData.data.dailyStatistic.amountByCategory,
  // );

  // const dailyStatistic = responseData.dailyStatistic;
  // console.log('daily 통계 데이터 입니다. : ', dailyStatistic); // 여기!!!

  const chartData = [40, 60, 30]; // Donut 차트에 표시할 데이터 배열
  const chartLabel = ['text1', 'text2', 'text3'];
  const chartTitle = 'My Donut Chart'; // 차트의 제목
  const showLegend = true; // 범례를 표시할지 여부
  const legendFontSize = '12px'; // 범례의 글꼴 크기
  const showLabels = true; // 라벨 표시 여부
  const labelFontSize = '14px'; // 라벨 글꼴 크기
  const labelColor = '#333'; // 라벨 텍스트 색상
  const showValues = true; // 값 표시 여부
  const valueFontSize = '16px'; // 값 글꼴 크기
  const valueColor = '#555'; // 값 텍스트 색상
  const chartColors = ['#ff5733', '#33ff57', '#5733ff']; // 차트의 섹션 색상 배열

  return (
    <div className="user">
      <div className="calender-container">
        <div className="calender-content">
          <CalenderHeader
            currentMonth={currentMonth}
            prevMonth={prevMonth}
            nextMonth={nextMonth}
          />
          <CalenderDays />
          <CalenderCells currentMonth={currentMonth} />
        </div>
      </div>
      <div className="chart-container">
        <DayMonthButton />
        <div className="chart-content">
          <DonutChart
            series={chartData}
            chartLabel={chartLabel}
            title={chartTitle}
            legendShow={showLegend}
            legendFont={legendFontSize}
            labelShow={showLabels}
            labelFont={labelFontSize}
            labelColor={labelColor}
            valueShow={showValues}
            valueFont={valueFontSize}
            valueColor={valueColor}
            colors={chartColors}
          />
        </div>
        <div className="bar-content">
          <div className="spend">
            <div className="spend-text-blue">현재 소비 금액 /</div>&nbsp;
            <div className="spend-text-black">목표 소비 금액</div>
          </div>
          <GoalBar
            color={'#2A4FFA'}
            current={100}
            goal={2000}
            type={'default'}
          />
          <br />
          <div className="spend-text-black">오늘의 고정 지출</div>
        </div>
      </div>
    </div>
  );
};

export default UserTemplate;
