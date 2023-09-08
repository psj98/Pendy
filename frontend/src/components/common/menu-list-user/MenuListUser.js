import React, { useState, useRef } from 'react';
import './MenuListUser.css';
import useClickOutside from '../../../hooks/useOutsideClick';
import { Icon } from '@iconify/react';
import { Link } from 'react-router-dom';

// 회원용 메뉴
const MenuListUser = () => {
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
          <Link to={'/setting'} className="link-content" onClick={onMenuToggle}>
            개인정보
          </Link>
        </div>
        <div className="sub-menu-container">
          <Link to={'/goal'} className="link-content" onClick={onMenuToggle}>
            목표설정
          </Link>
        </div>
        <div className="sub-menu-container">로그아웃</div>
      </div>
    </div>
  );
};

export default MenuListUser;
