import React from 'react';
import './Header.css';
import { Link } from 'react-router-dom';
import MenuListUser from '../menu-list-user/MenuListUser';
import MenuListGuest from '../menu-list-guest/MenuListGuset';

// 페이지 헤더
const Header = ({ isLoggedIn }) => {
  return (
    <div className="header">
      <div className="header-logo-container">
        <Link to={'/'} className="header-link-content">
          PENDY
        </Link>
      </div>
      <div className="header-menu-container">
        {isLoggedIn ? <MenuListUser /> : <MenuListGuest />}
      </div>
    </div>
  );
};

export default Header;
