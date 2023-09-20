import React from 'react';
import './DiaryTemplate.css';
import DiaryForm from '../../components/diary/DiaryForm';
import DonutChart from '../../components/common/donut-chart/DonutChart';
import GoalBar from '../../components/common/goal-bar/GoalBar';
import useDiaryDetail from '../../hooks/useDiaryDetail';

const DiaryTemplate = () => {
  const diaryDetail = useDiaryDetail(1);
  const series = [1, 1, 1, 1, 1, 1, 1, 1];
  const colors = [
    '#FAF2E8',
    '#BDECEA',
    '#DAB8F1',
    'rgba(243, 213, 182, 0.63)',
    'rgba(208, 228, 197, 0.42)',
    'rgba(255, 170, 180, 0.50)',
    '#CFE4C5',
    'rgba(189, 236, 235, 0.53)',
  ];

  console.log(diaryDetail);
  return (
    <div className="diary-template">
      <div className="diary-form-container">
        <DiaryForm />
      </div>
      <div className="diary-donutchart-container">
        <DonutChart
          series={series}
          title={'오늘 총 소비액'}
          legendShow={true}
          legendFont={20}
          labelShow={true}
          labelFont={18}
          labelColor={'black'}
          valueFont={16}
          valueShow={true}
          valueColor={'black'}
          colors={colors}
        />
      </div>
      <div className="diary-goal-container">
        <GoalBar />
      </div>
    </div>
  );
};

export default DiaryTemplate;
