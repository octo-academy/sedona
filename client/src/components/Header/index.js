import React from 'react';
import logo from '../../img/menu-logo.png';
// import Link from "react-router-dom/Link";
import './style.sass';

export const Header = () => (
    <header className="header">
        <nav className="menu">
            <ul className="menu__items">
                <li className="menu__item">
                    <a className="menu__link" href="info">Информация</a>
                </li>
                <li className="menu__item">
                    <a className="menu__link" href="gallery">Фото и Видео</a>
                </li>
                <li className="menu__item">
                    <a className="menu__link" href="map">Карта штата</a>
                </li>
                <li className="menu__item">
                    <a className="menu__link" href="hotels">Гостиницы</a>
                </li>
            </ul>
        </nav>
        <a href="/" className="logo">
            <img src="img/menu-logo.png" width="140" height="70" alt="City Sedona" />
        </a>
    </header>
);
