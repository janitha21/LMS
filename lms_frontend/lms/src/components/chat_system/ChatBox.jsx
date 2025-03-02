import { useState, useEffect } from "react";
import axios from "axios";
import "../chat_system/Chat.css";
import { jwtDecode as jwt_decode } from "jwt-decode";

const ChatBox = () => {
  const [chats, setChats] = useState([]); // Stores chat list
  const [selectedChat, setSelectedChat] = useState(null);
  const [messages, setMessages] = useState([]);
  const [message, setMessage] = useState("");

  const token = localStorage.getItem("token");
  const decodedToken = jwt_decode(token);

  const userId = decodedToken.userID;

  // Load chats when LMS loads
  useEffect(() => {
    const sample = userId; // Temporarily using this for testing

    axios
      .get(`http://localhost:8080/chat/get-chats?userId=${sample}`) // Adjust to your backend API
      .then((res) => setChats(res.data))
      .catch((err) => console.error("Error loading chats", err));
  }, 
  
  []);

  // Load messages when a chat is selected
  const loadMessages = (chat) => {
    setSelectedChat(chat); // Store the full chat object
    axios
      .get(`http://localhost:8080/message/get/${chat.chatId}`)
      .then((res) => setMessages(res.data.content)) // Page-based response
      .catch((err) => console.error("Error loading messages", err));
  };
 // Polling for new messages every 3 seconds
 useEffect(() => {
  if (!selectedChat) return;

  const interval = setInterval(() => {
    axios
      .get(`http://localhost:8080/message/get/${selectedChat.chatId}`)
      .then((res) => setMessages(res.data.content))
      .catch((err) => console.error("Error polling messages", err));
  }, 3000); // Fetch messages every 3 seconds
 

  console.log("run polling");
  
  return () => clearInterval(interval);
}, [selectedChat]);

  // Send message
  const sendMessage = () => {
    if (!message.trim()) return;

    const receiverId =
      userId === selectedChat.student.studentId
        ? selectedChat.mentor.mentorId
        : selectedChat.student.studentId;

    axios
      .post("http://localhost:8080/message/sent", {
        chatId: selectedChat.chatId,
        senderId: userId,
        reciverId: receiverId,
        msg: message,
      })
      .then((res) => {
        setMessages([...messages, res.data]); // Add new message
        setMessage(""); // Clear message input
        loadMessages(selectedChat);
      })

      .catch((err) => console.error("Error sending message", err));
  };

  return (
    <div className="chat-container">
      {/* Chat List */}
      <div className="chat-list">
        <h3>Chats</h3>
        {chats.map((chat) => (
          <div key={chat.chatId} onClick={() => loadMessages(chat)}>
            {userId === chat.student.studentId
              ? chat.mentor.mentorName
              : chat.student.studentName}
          </div>
        ))}
      </div>

      {/* Chat Messages */}
      {selectedChat && (
        <div className="chat-box">
          <h3>Chat {selectedChat.chatId}</h3>
          <div className="messages">
            {messages.map((msg, index) => (
              <div
                key={index}
                className={msg.senderId === userId ? "sent" : "received"}
              >
                {msg.msg}
              </div>
            ))}
          </div>

          {/* Send Message */}
          <div className="message-input">
            <input
              type="text"
              value={message}
              onChange={(e) => setMessage(e.target.value)}
              placeholder="Type a message..."
            />
            <button onClick={sendMessage}>Send</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default ChatBox;
