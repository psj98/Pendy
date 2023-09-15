import React, { useState, useRef } from 'react';
import './MenuListUser.css';
import useClickOutside from '../../../hooks/useOutsideClick';
import { Icon } from '@iconify/react';
import { Link, useNavigate } from 'react-router-dom';

// 회원용 메뉴
const MenuListUser = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const navigate = useNavigate();
  const menuRef = useRef(null);

  // 메뉴가 아닌 위치 클릭 시 메뉴 비활성화
  useClickOutside(menuRef, () => {
    setIsMenuOpen(false);
  });

  // 메뉴 활성화
  const onMenuToggle = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  //로그아웃 클릭
  const onClickLogout = () => {
    console.log('logout');
    localStorage.removeItem('accessToken');
    sessionStorage.removeItem('email');
    sessionStorage.removeItem('name');
    sessionStorage.removeItem('age');
    sessionStorage.removeItem('salary');
    sessionStorage.removeItem('accountList');
    navigate('/', { replace: true });
  };

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
        <div className="sub-menu-container" onClick={onClickLogout}>
          로그아웃
        </div>
      </div>
    </div>
  );
};

export default MenuListUser;
