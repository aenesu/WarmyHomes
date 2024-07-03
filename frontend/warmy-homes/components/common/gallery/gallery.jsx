'use client'
import { useState } from 'react';
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

  return (
    <div className={styles.gallery}>
      <div className={styles.mainImage}>
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
    </div>
  );
}
