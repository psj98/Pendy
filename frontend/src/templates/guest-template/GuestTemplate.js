import React from 'react';
import './GuestTemplate.css';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';

const GuestTemplate = () => {
  const navigate = useNavigate();

  const handleButtonClick = () => {
    const path = `/login`;
    navigate(path);
  };

  return (
    <div className="guest-template">
      <img src="/main.png" alt="Guest Main" className="background-image" />

      <div className="startnow" onClick={handleButtonClick}>
        <div className="elements">
          <p className="text-1">지금 시작하기</p>
        </div>
      </div>

      <div className="header-logo-container">
        <Link to={'/login'} className="header-link-content">
          PENDY
        </Link>
      </div>
    </div>
  );
};

export default GuestTemplate;
