import React, { useState, useRef } from 'react';
import './MenuListGuest.css';
import useClickOutside from '../../../hooks/useClickOutside.js';
import { Icon } from '@iconify/react';
import { Link } from 'react-router-dom';

// 비회원용 메뉴
const MenuListGuest = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const menuRef = useRef(null);

  // 메뉴 활성화
  const onMenuToggle = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  // 메뉴가 아닌 위치 클릭 시 메뉴 비활성화
  useClickOutside(menuRef, () => {
    setIsMenuOpen(false);
  });

  return (
    <div className="menu-list" ref={menuRef}>
      <div className="hamburger-menu" onClick={onMenuToggle}>
        <Icon icon="quill:hamburger" />
      </div>
      <div className={`sub-menu ${isMenuOpen ? 'show' : ''}`}>
        <div className="sub-menu-container">
          <Link to={'/login'} className="link-content" onClick={onMenuToggle}>
            로그인
          </Link>
        </div>
        <div className="sub-menu-container">
          <Link
            to={'/login/signup'}
            className="link-content"
            onClick={onMenuToggle}
          >
            회원가입
          </Link>
        </div>
      </div>
    </div>
  );
};

export default MenuListGuest;
