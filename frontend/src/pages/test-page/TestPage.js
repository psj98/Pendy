import React, { useState } from 'react';
import './TestPage.css';
import EmotionModal from '../../components/modal/emotion-modal/EmotionModal';
import ChatBot from '../../components/common/chat-bot/ChatBot';

// import DonutChart from '../../components/common/donut-chart/DonutChart';
// import Header from '../../components/common/header/Header';
// import BarChart from '../../components/common/bar-chart/BarChart';
// import ChartCategory from '../../components/common/chart-category/ChartCategory';

const TestPage = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  //모달창 열기
  const openModal = () => {
    setIsModalOpen(true);
  };

  // 모달 닫기
  const closeModal = () => {
    setIsModalOpen(false);
  };

  return (
    <div className="test-page">
      <button onClick={() => openModal()}>버튼 클릭</button>
      {isModalOpen && <EmotionModal closeModal={closeModal} />}
      <div className="test-chat">
        <ChatBot />
      </div>
    </div>
  );
};

export default TestPage;
