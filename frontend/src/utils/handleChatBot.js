import chatBotAxiosCreate from '../chatBotAxiosCreate';

const handleChatBot = (preMessage, message) => {
  console.log('handleChatBot');

  // Python에 전달할 챗봇 메시지 형식
  const ChatBotRequest = {
    chatBotMessage: {
      preMessage: preMessage,
      tempMessage: message,
    },
  };

  const serverUrl = '/ml/chatbot';

  return chatBotAxiosCreate.post(serverUrl, ChatBotRequest);
};

export default handleChatBot;
