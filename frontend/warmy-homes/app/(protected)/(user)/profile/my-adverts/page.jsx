"use client";
import MyAdvertsCard from '@/components/user-panel/my-adverts-card/my-adverts-card';
import React, { useState, useEffect } from 'react';
import styles from './my-adverts.module.scss';
import Banner from '@/components/common/banner/banner';

export default function MyAdverts() {
  // State for pagination
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 3; // Number of items per page

  // Data for adverts
  const myAdverts = [
    { id: 1, title: "Equestrian Family Home", city_id: "CA City", price: 1400, create_at: "01/06/2024", status: "Pending", view_count: "125" },
    { id: 2, title: "Luxury Apartment", city_id: "NY City", price: 2100, create_at: "05/08/2024", status: "Approved", view_count: "175" },
    { id: 3, title: "Suburban House", city_id: "Houston", price: 950, create_at: "20/07/2024", status: "Pending", view_count: "89" },
    { id: 4, title: "Beachside Villa", city_id: "Miami", price: 3000, create_at: "10/09/2024", status: "Rejected", view_count: "210" },
    // Add more adverts as necessary
  ];

  // Pagination calculation
  const totalPages = Math.ceil(myAdverts.length / itemsPerPage);
  const startIndex = (currentPage - 1) * itemsPerPage;
  const paginatedAdverts = myAdverts.slice(startIndex, startIndex + itemsPerPage);

  const handlePageChange = (direction) => {
    setCurrentPage((prevPage) => prevPage + direction);
  };

  // Log current page and paginated adverts on change
  useEffect(() => {
    console.log('Current Page:', currentPage);
    console.log('Paginated Adverts:', paginatedAdverts);
  }, [currentPage, paginatedAdverts]);

  return (
    <div className={styles.container}>
      <Banner title="MY ADVERTS" />
      <div className={styles.words}>
        <h4>Property</h4>
        <h4>Date Published</h4>
        <h4>Status</h4>
        <h4>View/Like/Tour</h4>
        <h4>Action</h4>
      </div>
      <div className={styles.cards}>
        {paginatedAdverts.map(({ id, title, city_id, price, create_at, status, view_count }) => (
          <MyAdvertsCard
            key={id}
            {...{ title, city_id, price, create_at, status, view_count }}
          />
        ))}
      </div>
      <div className={styles.pagination}>
        <button
          className={`${styles.pageButton} ${currentPage === 1 ? styles.disabled : ''}`}
          onClick={() => handlePageChange(-1)}
          disabled={currentPage === 1}
        >
          <img src="/assets/vectors/arrowL.svg" alt="Previous" />
        </button>
        <span className={styles.pageInfo}>
          {currentPage} / {totalPages}
        </span>
        <button
          className={`${styles.pageButton} ${currentPage === totalPages ? styles.disabled : ''}`}
          onClick={() => handlePageChange(1)}
          disabled={currentPage === totalPages}
        >
          <img src="/assets/vectors/arrow.svg" alt="Next" />
        </button>
      </div>
    </div>
  );
}
