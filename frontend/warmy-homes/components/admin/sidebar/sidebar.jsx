"use client";
import { useState, useEffect } from "react";
import styles from './sidebar.module.scss';
import Image from 'next/image';
import { PiListDashesFill } from "react-icons/pi";

export default function Sidebar() {
  const [isOpen, setIsOpen] = useState(false);
  const [currentSection, setCurrentSection] = useState('Dashboard');

  const toggleSidebar = () => {
    setIsOpen(prev => !prev);
  };

  const handleNavClick = (section) => {
    setCurrentSection(section);
    setIsOpen(false); 
  };


  useEffect(() => {
    const handleClickOutside = (event) => {
      if (isOpen && !event.target.closest(`.${styles.sidebar}`)) {
        setIsOpen(false);
      }
    };

    document.addEventListener('click', handleClickOutside);
    return () => document.removeEventListener('click', handleClickOutside);
  }, [isOpen]);

  return (
    <>
      <div className={styles.header}>
        <button className={styles.toggleButton} onClick={toggleSidebar}>
          <PiListDashesFill className={styles.icon} />
          <span className={styles.homeText}>Home / {currentSection}</span>
        </button>
      </div>
      <div className={`${styles.sidebar} ${isOpen ? styles.open : ''}`}>
        <div className={styles.avatarContainer}>
          <Image 
            src="/assets/images/logo-white 2.png"
            alt="Logo"
            className={styles.logo}
            width={140}
            height={110}
          />
        </div>
        <nav className={styles.navLinks}>
          <a href="#dashboard" onClick={() => handleNavClick('Dashboard')}>Dashboard</a>
          <a href="#adverts" onClick={() => handleNavClick('Adverts')}>Adverts</a>
          <a href="#categories" onClick={() => handleNavClick('Categories')}>Categories</a>
          <a href="#advertTypes" onClick={() => handleNavClick('Advert Types')}>Advert Types</a>
          <a href="#users" onClick={() => handleNavClick('Users')}>Users</a>
          <a href="#tourRequests" onClick={() => handleNavClick('Tour Requests')}>Tour Requests</a>
          <a href="#reports" onClick={() => handleNavClick('Reports')}>Reports</a>
          <a href="#contactMessages" onClick={() => handleNavClick('Contact Messages')}>Contact Messages</a>
          <a href="#settings" onClick={() => handleNavClick('Settings')}>Settings</a>
          <a href="#webSite" onClick={() => handleNavClick('Web Site')}>Web Site</a>
          <a href="#logout" onClick={() => handleNavClick('Logout')}>Logout</a>
        </nav>
        <button className={styles.closeButton} onClick={toggleSidebar}>
          <PiListDashesFill className={styles.icon} />
        </button>
      </div>
    </>
  );
}
