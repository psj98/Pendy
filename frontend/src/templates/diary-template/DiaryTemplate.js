import React from 'react';
import './DiaryTemplate.css';
import DiaryForm from '../../components/diary/DiaryForm';
import DonutChart from '../../components/common/donut-chart/DonutChart';
import GoalBar from '../../components/common/goal-bar/GoalBar';
import useDiaryDetail from '../../hooks/useDiaryDetail';
import useTodayList from '../../hooks/useTodayList';
import EmotionModal from '../../components/modal/emotion-modal/EmotionModal';
import { useParams } from 'react-router-dom';

const DiaryTemplate = () => {
  const { id } = useParams();
  const { diaryDetail, diaryLoading } = useDiaryDetail(id);
  const { todayList, todayLoading } = useTodayList();

  if (diaryLoading) {
    return <div>Loading...</div>;
  }
  if (todayLoading) {
    return <div>Loading...</div>;
  }

  let isModalOpen = false;
  const regDate = diaryDetail.data.diary.regDate;
  const title = diaryDetail.data.diary.title;
  const content = diaryDetail.data.diary.content;
  const comment = diaryDetail.data.diary.comment;
  const stampType = diaryDetail.data.diary.stampType;

  console.log('diaryDetail', diaryDetail.data);
  console.log('todayList', todayList.data);

  if (todayList.length !== 0) {
    console.log('modal open');
    isModalOpen = true;
  }

  const series = diaryDetail.data.dailyStatistic.amountByCategory.map(
    (item) => item.amount,
  );
  const chartColors = [
    '#FAF2E8',
    '#BDECEA',
    '#DAB8F1',
    'rgba(243, 213, 182, 0.63)',
    'rgba(208, 228, 197, 0.42)',
    'rgba(255, 170, 180, 0.50)',
    '#CFE4C5',
    'rgba(189, 236, 235, 0.53)',
  ];

  // 해당 월의 마지막날 구하기
  const dateObject = new Date(regDate);
  dateObject.setMonth(dateObject.getMonth() + 1, 0);
  const lastDayOfMonth = dateObject.getDate();

  // 모달 닫기
  const closeModal = () => {
    isModalOpen = false;
  };

  return (
    <div className="diary-container">
      <div className="diary-template">
        <div className="diary-form-container">
          <div className="diary-name">일기장</div>
          <DiaryForm
            regDate={regDate}
            title={title}
            content={content}
            comment={comment}
            stampType={stampType}
          />
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
            colors={chartColors}
          />
          <div className="chart-legend"></div>
        </div>
        <div className="diary-goal-container">
          <div className="diary-goal-title">
            <p>남은 목표 금액</p>
          </div>
          {diaryDetail.data.goalByCategory.map((goalByCategory, index) => (
            <div key={index} className="diary-goal">
              <div className="diary-goal-category">
                {goalByCategory.categoryName}
              </div>
              <GoalBar
                color={chartColors[index]}
                current={series[index]}
                goal={goalByCategory.categoryGoalAmount / lastDayOfMonth}
                type={'rectangle'}
              />
            </div>
          ))}
        </div>
      </div>

      {/* 모달 창 */}
      {isModalOpen && (
        <EmotionModal closeModal={closeModal} todayList={todayList} />
      )}
    </div>
  );
};

export default DiaryTemplate;
