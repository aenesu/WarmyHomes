'use client'
import { useEffect, useRef, useState } from 'react';
import styles from './gallery.module.scss';

const images = [
  '/assets/images/mock/mockdata1.jpeg',
  '/assets/images/mock/mockdata2.jpg',
  '/assets/images/mock/mockdata2.webp',
  '/assets/images/mock/mockdata3.jpeg',
  '/assets/images/mock/mockdata4.webp',
  '/assets/images/mock/mockdata1.jpeg',
  '/assets/images/mock/mockdata2.jpg',
  '/assets/images/mock/mockdata2.webp',
];

export default function Gallery() {
  const [currentIndex, setCurrentIndex] = useState(3); // Start at index 3
  const [showPopup, setShowPopup] = useState(false);
  const [popupVisible, setPopupVisible] = useState(false);
  const popupRef = useRef(null);

  const togglePopup = () => {
    if (showPopup) {
      setPopupVisible(false);
      setTimeout(() => setShowPopup(false), 300);
      document.removeEventListener('click', handleOutsideClick); // Remove listener on close
    } else {
      setShowPopup(true);
      setTimeout(() => setPopupVisible(true), 10);
      document.addEventListener('click', handleOutsideClick); // Add listener on open
    }
  };

  const handleOutsideClick = (e) => {
    if (popupRef.current && !popupRef.current.contains(e.target)) {
      setPopupVisible(false);
      setTimeout(() => setShowPopup(false), 300);
      document.removeEventListener('click', handleOutsideClick);
    }
  };

  const handlePrev = () => {
    setCurrentIndex((prevIndex) => (prevIndex === 0 ? images.length - 1 : prevIndex - 1));
  };

  const handleNext = () => {
    setCurrentIndex((prevIndex) => (prevIndex === images.length - 1 ? 0 : prevIndex + 1));
  };

  return (
    <div className={styles.gallery}>
      <div className={styles.mainImageContainer}>
        <div className={styles.mainImage} onClick={togglePopup}>
          <img id="largeImage" src={images[currentIndex]} alt="Main Image" />
        </div>
      </div>

      <div className={styles.thumbnailImages}>
        <button className={styles.navButton} onClick={handlePrev}>«</button>
        <div className={styles.thumbnailImagesContainer}>
          {images.map((src, index) => (
            <img
              key={index}
              className={styles.thumbnail}
              src={src}
              alt={`Image ${index + 1}`}
              onClick={() => setCurrentIndex(index)}
            />
          ))}
        </div>
        <button className={styles.navButton} onClick={handleNext}>»</button>
      </div>

      {showPopup && (
        <div className={`${styles.popup} ${popupVisible ? styles.visible : ''}`} >
          <div className={styles.popupContent} ref={popupRef} onClick={(e) => e.stopPropagation()} >
            <button className={styles.popNavButton} onClick={(e) => { e.stopPropagation(); handlePrev()}} >«</button>
            <img src={images[currentIndex]} alt="Congrats! You Have Seen the Bigger Picture" />
            <button className={styles.popNavButton} onClick={(e) => { e.stopPropagation(); handleNext()}} >»</button>
          </div>
        </div>
      )}
    </div>
  );
}

