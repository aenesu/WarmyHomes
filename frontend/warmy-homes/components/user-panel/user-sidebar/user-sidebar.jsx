"use client";
import { useState, useEffect } from "react";
import styles from './user-sidebar.module.scss';
import Image from 'next/image';
import { PiListDashesFill } from "react-icons/pi";

export default function UserSidebar() {
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
          <a href="dashboard" onClick={() => handleNavClick('Dashboard')}>My Profile</a>
          <a href="properties" onClick={() => handleNavClick('Adverts')}>Change Password</a>
          <a href="categories" onClick={() => handleNavClick('Categories')}>My Properties</a>
          <a href="adverts-types" onClick={() => handleNavClick('Advert Types')}>Add Property</a>
          <a href="users" onClick={() => handleNavClick('Users')}>Tour Requests</a>
          <a href="tour-requests" onClick={() => handleNavClick('Tour Requests')}>Logout</a>
        </nav>
        <button className={styles.closeButton} onClick={toggleSidebar}>
          <PiListDashesFill className={styles.icon} />
        </button>
      </div>
    </>
  );
}
