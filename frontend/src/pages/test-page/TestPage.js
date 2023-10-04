import React from 'react';
import './TestPage.css';
import ChatBot from '../../components/common/chat-bot/ChatBot';

const TestPage = () => {
  return (
    <div className="test-page">
      <div className="test-chat">
        <ChatBot />
      </div>
    </div>
  );
};

export default TestPage;
