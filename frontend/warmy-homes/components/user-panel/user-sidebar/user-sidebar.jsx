"use client";
import { useState, useEffect } from "react";
import styles from './user-sidebar.module.scss';
import Image from 'next/image';
import { PiListDashesFill } from "react-icons/pi";
import Link from "next/link";

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
            width={156}
            height={36}
          />
         
          <button className={styles.closeButton} onClick={toggleSidebar}>
           x
          </button>
        </div>
        <nav className={styles.navLinks}>
          <Link href="/profile/my-adverts">My Properties</Link>
          <Link href="/profile/tour-requests">My Tour Requests</Link>
          <div className={styles.divider}></div>
          <Link href="/new-advert">Add New Property</Link>
          <div className={styles.divider}></div>
          <div className={styles.divider}></div>
          <Link href="/profile">My Profile</Link>
          <Link href="/profile/password/change">Change Password</Link>
          <Link href="/">Logout</Link>
        </nav>
      </div>
    </>
  );
}
