// 1원 인증 요청
import chatBotAxiosCreate from '../chatBotAxiosCreate';

const handleChatBot = (message) => {
  console.log('handleChatBot');
  const chatBotMessage = {
    tempMessage: "",
    preMessage: message,
  };

  console.log(chatBotMessage)

  const serverUrl = '/ml/chatbot';

  return chatBotAxiosCreate.post(serverUrl, chatBotMessage);
};

export default handleChatBot;
