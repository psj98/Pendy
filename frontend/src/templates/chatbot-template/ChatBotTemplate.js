import React, { useState } from 'react';
import './ChatBotTemplate.css';

import handleChatBot from '../../utils/handleChatBot';

const ChatBotTemplate = () => {
  const [message, setMessage] = useState('');

  // Python으로 message 전달
  const onSendMessageClick = async (event) => {
    event.preventDefault();

    const response = await handleChatBot(message);
    console.log(response);

    setMessage(''); // message 초기화
  };

  // Enter 키를 누른 경우 onSendCodeClick 함수 실행
  const onPressEnterKey = (event) => {
    if (event.key === 'Enter') {
      event.preventDefault();

      onSendMessageClick(event);
    }
  };

  return (
    <div className="ChatBot">
      <div>
        <div>
          <input
            type="text"
            name="message"
            placeholder="무엇이든 물어보세요"
            value={message}
            onChange={(e) => setMessage(e.target.value)}
            onKeyDown={onPressEnterKey}
          />
        </div>
        <div>
          <button onClick={onSendMessageClick}>보내기</button>
        </div>
      </div>
    </div>
  );
};

export default ChatBotTemplate;
