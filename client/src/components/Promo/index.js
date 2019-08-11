import React from 'react';
import promo from '../../img/slogan.png';
import './style.css';

export const Promo = () => (
    <section className="promo">
        <h2 className="visually-hidden">Welcome to Sedona!</h2>
        <img className="sedona-promo" src={promo} width="460" height="350" alt="Welcome to Sedona!" />
    </section>
);
