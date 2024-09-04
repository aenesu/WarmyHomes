"use client";
import { useState, useEffect } from "react";
import Link from "next/link";  // Import Next.js Link component
import { useRouter } from "next/navigation"; // Import Next.js router
import styles from './sidebar.module.scss';
import Image from 'next/image';
import { PiListDashesFill } from "react-icons/pi";

export default function Sidebar() {
  const [isOpen, setIsOpen] = useState(false);
  const [currentSection, setCurrentSection] = useState('Dashboard');
  const router = useRouter(); // Initialize router
  const baseUrl = "/dashboard/admin"; // Define a base URL or dynamic URL (e.g., `/admin`, `/user`, etc.)

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
            width={156}
            height={36}
          />
        </div>
        <nav className={styles.navLinks}>
          {/* Correctly use template literals inside curly braces */}
          <Link href={`/dashboard`} onClick={() => handleNavClick('Dashboard')}>Dashboard</Link>
          <Link href={`${baseUrl}/properties`} onClick={() => handleNavClick('Adverts')}>Adverts</Link>
          <Link href={`${baseUrl}/categories`} onClick={() => handleNavClick('Categories')}>Categories</Link>
          <Link href={`${baseUrl}/adverts-types`} onClick={() => handleNavClick('Advert Types')}>Advert Types</Link>
          <Link href={`${baseUrl}/users`} onClick={() => handleNavClick('Users')}>Users</Link>
          <Link href={`${baseUrl}/tour-requests`} onClick={() => handleNavClick('Tour Requests')}>Tour Requests</Link>
          <Link href={`${baseUrl}/reports`} onClick={() => handleNavClick('Reports')}>Reports</Link>
          <Link href={`${baseUrl}/contact-messages`} onClick={() => handleNavClick('Contact Messages')}>Contact Messages</Link>
          <Link href={`${baseUrl}/settings`} onClick={() => handleNavClick('Settings')}>Settings</Link>
          <Link href="/" target="_blank" onClick={() => handleNavClick('Web Site')}>Web Site</Link>
          <Link href={`${baseUrl}/logout`} onClick={() => handleNavClick('Logout')}>Logout</Link>
        </nav>
        <button className={styles.closeButton} onClick={toggleSidebar}>
          <PiListDashesFill className={styles.icon} />
        </button>
      </div>
    </>
  );
}
