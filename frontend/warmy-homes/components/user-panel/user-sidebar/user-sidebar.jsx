"use client";
import { useState, useEffect } from "react";
import styles from './user-sidebar.module.scss';
import Image from 'next/image';
import { PiListDashesFill } from "react-icons/pi";

export default function UserSidebar() {
  const [isOpen, setIsOpen] = useState(false);

  const toggleSidebar = () => {
    setIsOpen(prev => !prev);
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
      {!isOpen && (
        <div className={styles.toggleButton} onClick={toggleSidebar}>
          <PiListDashesFill className={styles.icon} />
        </div>
      )}

      <div className={`${styles.sidebar} ${isOpen ? styles.open : ''}`}>
        <div className={styles.avatarContainer}>
          <Image 
            src="/assets/images/logo-white2.png"
            alt="Logo"
            className={styles.logo}
            width={140}
            height={110}
          />
         
          <button className={styles.closeButton} onClick={toggleSidebar}>
           x
          </button>
        </div>
        <nav className={styles.navLinks}>
          <a href="dashboard">My Profile</a>
          <a href="properties">Change Password</a>
          <a href="categories">My Properties</a>
          <a href="adverts-types">Add Property</a>
          <a href="tour-requests">Tour Requests</a>
          <a href="#">Logout</a>
        </nav>
      </div>
    </>
  );
}
