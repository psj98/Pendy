import React from 'react';
import './DiaryTemplate.css';
import DiaryForm from '../../components/diary/DiaryForm';
import DonutChart from '../../components/common/donut-chart/DonutChart';
import GoalBar from '../../components/common/goal-bar/GoalBar';
import useDiaryDetail from '../../hooks/useDiaryDetail';

const DiaryTemplate = () => {
  const { diaryDetail, loading } = useDiaryDetail(1);
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

  if (loading) {
    return <div>Loading...</div>;
  }

  console.log('diaryDetail');
  console.log(diaryDetail);
  console.log(diaryDetail.data.diary);
  const id = diaryDetail.data.diary.id;
  return (
    <div className="diary-container">
      <div className="diary-template">
        <div className="diary-form-container">
          <div className="diary-title">
            <p>일기장</p>
          </div>
          <DiaryForm id={id} />
        </div>
        <div className="diary-donutchart-container">
          <div className="diary-donutchart-title">
            <p>오늘 소비 분석</p>
          </div>
          <DonutChart
            series={series}
            title={'오늘 총 소비액'}
            legendShow={false}
            legendFont={20}
            labelShow={true}
            labelFont={18}
            labelColor={'black'}
            valueFont={16}
            valueShow={true}
            valueColor={'black'}
            colors={colors}
          />
          <div className="chart-legend"></div>
        </div>
        <div className="diary-goal-container">
          <div className="diary-goal-title">
            <p>남은 목표 금액</p>
          </div>
          <div className="diary-goal">
            <GoalBar
              color={'#FAF2E8'}
              current={120}
              goal={200}
              type={'rectangle'}
            />
          </div>
          <div className="diary-goal">
            <GoalBar
              color={'#BDECEA'}
              current={120}
              goal={200}
              type={'rectangle'}
            />
          </div>
          <div className="diary-goal">
            <GoalBar
              color={'#DAB8F1'}
              current={120}
              goal={200}
              type={'rectangle'}
            />
          </div>
          <div className="diary-goal">
            <GoalBar
              color={'rgba(243, 213, 182, 0.63)'}
              current={60}
              goal={200}
              type={'rectangle'}
            />
          </div>
          <div className="diary-goal">
            <GoalBar
              color={'rgba(208, 228, 197, 0.42)'}
              current={120}
              goal={200}
              type={'rectangle'}
            />
          </div>
          <div className="diary-goal">
            <GoalBar
              color={'rgba(255, 170, 180, 0.50)'}
              current={120}
              goal={200}
              type={'rectangle'}
            />
          </div>
          <div className="diary-goal">
            <GoalBar
              color={'#CFE4C5'}
              current={120}
              goal={200}
              type={'rectangle'}
            />
          </div>
          <div className="diary-goal">
            <GoalBar
              color={'rgba(189, 236, 235, 0.53)'}
              current={120}
              goal={200}
              type={'rectangle'}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default DiaryTemplate;
