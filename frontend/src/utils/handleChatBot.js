// 1원 인증 요청
import chatBotAxiosCreate from '../chatBotAxiosCreate';

const handleChatBot = (preMessage, message) => {
  console.log('handleChatBot');
  const ChatBotRequest = {
    chatBotMessage: {
      preMessage: preMessage,
      tempMessage: message,
    },
  };

  console.log(ChatBotRequest);

  const serverUrl = '/ml/chatbot';

  return chatBotAxiosCreate.post(serverUrl, ChatBotRequest);
};

export default handleChatBot;
