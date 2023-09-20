// 1원 인증 요청
import chatBotAxiosCreate from '../chatBotAxiosCreate';

const handleChatBot = (message) => {
  console.log('handleChatBot');
  const data = {
    message: message,
  };
  const serverUrl = '/ml/chatbot';

  return chatBotAxiosCreate.post(serverUrl, data);
};

export default handleChatBot;
