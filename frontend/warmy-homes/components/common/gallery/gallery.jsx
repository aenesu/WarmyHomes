'use client'
import { useEffect, useState } from 'react';
import styles from './gallery.module.scss';

const images = [
  '/assets/images/mock/mockdata1.jpeg',
  '/assets/images/mock/mockdata2.jpg',
  '/assets/images/mock/mockdata2.webp',
  '/assets/images/mock/mockdata3.jpeg',
  '/assets/images/mock/mockdata4.webp'
];

export default function Gallery() {
  const [mainImage, setMainImage] = useState(images[3]);
  const [showPopup, setShowPopup] = useState(false);
  const [popupVisible, setPopupVisible] = useState(false);

  //    const togglePopup = () => {
  //      setShowPopup(!showPopup);
  //    };

  const togglePopup = () => {
    if (showPopup) {
      setPopupVisible(false);
      setTimeout(() => setShowPopup(false), 300);
    } else {
      setShowPopup(true);
      setTimeout(() => setPopupVisible(true), 10);
    }
  };


//  useEffect(() => {
//    if (popupVisible) {
//      document.body.classList.add('no-scroll');
//    } else {
//      document.body.classList.remove('no-scroll');
//    }
//
//    return () => {
//      document.body.classList.remove('no-scroll');
//    };
//  }, [popupVisible]);


  return (
    <div className={styles.gallery}>

      <div className={styles.mainImage} onClick={togglePopup}>
        <img id="largeImage" src={mainImage} alt="Main Image" />
      </div>

      <div className={styles.thumbnailImages}>
        {images.map((src, index) => (
          <img
            key={index}
            className={styles.thumbnail}
            src={src}
            alt={`Image ${index + 1}`}
            onClick={() => setMainImage(src)}
          />
        ))}
      </div>

      {showPopup && (
        <div className={`${styles.popup} ${popupVisible ? styles.visible : ''}`} onClick={togglePopup}>
          <div className={styles.popupContent}>
            <img src={mainImage} alt="Congrats! You Have Seen the Bigger Picture" />
          </div>
        </div>
      )}

    </div>
  );
}
