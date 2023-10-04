import React, { useState, useEffect } from 'react';
import './ChatBot.css';

import handleChatBot from '../../../utils/handleChatBot';

const ChatBot = () => {
  const [isChatBotOpen, setIsChatBotOpen] = useState(false);
  const [sendMessage, setSendMessage] = useState(''); // 보낼 메시지
  const [preMessage, setPreMessage] = useState(''); // 이전 메시지
  const [messageList, setMessageList] = useState([{}]); // 채팅 내역

  useEffect(() => {
    setMessageList(JSON.parse(localStorage.getItem('messageList')) || []);
  }, []);

  // Python으로 message 전달
  const onSendMessageClick = async (event) => {
    event.preventDefault();

    const localMessageList =
      JSON.parse(localStorage.getItem('messageList')) || []; // 메시지 배열 localStorage에서 가져오기

    setPreMessage(sendMessage); // 이전 메시지

    const response = await handleChatBot(preMessage, sendMessage);

    // text to speech
    const synth = window.speechSynthesis;
    const msg = new SpeechSynthesisUtterance();
    msg.lang = "ko-KR";
    msg.pitch = 1;
    msg.rate = 1;
    msg.text = response.data.message;
    msg.volume = 1;
    synth.speak(msg);

    setPreMessage(message); // 이전 message
    setMessage(''); // message 초기화

    const newMessage = {
      sendMessage: sendMessage,
      responseMessage: response.data.message,
    };

    localMessageList.push(newMessage);

    localStorage.setItem('messageList', JSON.stringify(localMessageList)); // 전송 메시지 localStorage에 저장
    setMessageList(localMessageList);

    console.log(messageList);

    setSendMessage(''); // message 초기화
  };

  // Enter 키를 누른 경우 onSendMessageClick 함수 실행
  const onPressEnterKey = (event) => {
    if (event.key === 'Enter') {
      event.preventDefault();

      onSendMessageClick(event);
    }
  };

  // 챗봇 열기
  const openChatBot = () => {
    setIsChatBotOpen(true);
  };

  // 챗봇 닫기
  const closeChatBot = () => {
    setIsChatBotOpen(false);
  };

  return (
    <div className="chatbot-container">
      {/* 챗봇 채팅 화면 */}
      {isChatBotOpen && (
        <div className="chatbot-main-container">
          {/* 창 닫기 버튼 */}
          <div className="chatbot-close-btn-div">
            <button className="chatbot-close-btn" onClick={closeChatBot}>
              X
            </button>
          </div>

          {/* 챗봇 채팅 텍스트 */}
          <div className="chatbot-main-text-div">
            <div className="chatbot-message-div">
              {messageList.map((message) => (
                <>
                  <div className="send-message-container">
                    <div className="send-message-div">
                      {message.sendMessage}
                    </div>
                  </div>
                  <div className="response-message-container">
                    <div className="response-message-div">
                      {message.responseMessage}
                    </div>
                  </div>
                </>
              ))}
            </div>
          </div>

          <div className="chatbot-text-container">
            {/* 챗봇 대화 입력 */}
            <div className="chatbot-question-text">
              <input
                type="text"
                name="message"
                placeholder="무엇이든 물어보세요"
                className="chatbot-question-text-input"
                value={sendMessage}
                onChange={(e) => setSendMessage(e.target.value)}
                onKeyDown={onPressEnterKey}
              />
            </div>

            {/* 보내기 버튼 */}
            <div className="chatbot-send-btn-div">
              <button className="chatbot-send-btn" onClick={onSendMessageClick}>
                보내기
              </button>
            </div>
          </div>
        </div>
      )}

      {/* 챗봇 마크 */}
      <div
        className="chatbot-main-btn"
        onClick={!isChatBotOpen ? openChatBot : closeChatBot}
      >
        <img
          className="chatbot-img"
          src="/chatbot-img/namani-chatbot.png"
          alt="보통"
        />
      </div>
    </div>
  );
};

export default ChatBot;
