"use client";
import React, { useState, useEffect } from 'react';
import styles from './admin-tour-request.module.scss';
import MyTourRequestCard from '@/components/user-panel/my-tour-request-card/my-tour-card';

export default function Properties() {
  const [formData, setFormData] = useState({
    search: '',
    category: '',
    type: '',
    status: ''
  });

  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 5;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Search submitted with data:', formData);
  };

  const handlePageChange = (direction) => {
    setCurrentPage((prevPage) => prevPage + direction);
  };

  const myAdverts = [
    // Example adverts
    { id: 1, title: 'Equestrian Family Home', country_id: 'USA', city_id: 'CA', district_id: 'California City', price: 1400, category_id: 'John Doe', status: 'Pending', advert_type_id: '10/10/2023', time_id: '10:30 PM' },
    { id: 2, title: 'Equestrian Family Home', country_id: 'USA', city_id: 'CA', district_id: 'California City', price: 1400, category_id: 'John Doe', status: 'Pending', advert_type_id: '10/10/2023', time_id: '10:30 PM' }
  ];

  const totalPages = Math.ceil(myAdverts.length / itemsPerPage);
  const startIndex = (currentPage - 1) * itemsPerPage;
  const paginatedAdverts = myAdverts.slice(startIndex, startIndex + itemsPerPage);

  // Debugging logs
  useEffect(() => {
    console.log('Current Page:', currentPage);
    console.log('Paginated Adverts:', paginatedAdverts);
  }, [currentPage, paginatedAdverts]);

  return (
    <div className={styles.mainContainer}>
      <form className={styles.searchForm} onSubmit={handleSubmit}>
        <input
          type="text"
          name="search"
          placeholder="Enter your text here"
          value={formData.search}
          onChange={handleChange}
          className={styles.inputField}
        />
        <button className={styles.searchButton}>
          <img src="/assets/vectors/magnify.svg" alt="Search" />
        </button>
      </form>
      <div className={styles.container}>
        <div className={styles.words}>
          <h4>Property</h4>
          <h4>Owner</h4>
          <h4>Status</h4>
          <h4>Tour Request Date</h4>
          <h4>Tour Request Time</h4>
          <h4>Action</h4>
        </div>
        <div className={styles.cards}>
          {paginatedAdverts.map(({ id, title, country_id, city_id, district_id, category_id, advert_type_id, price, status, time_id }) => (
            <MyTourRequestCard
              key={id}
              {...{
                title,
                country_id,
                city_id,
                district_id,
                category_id,
                advert_type_id,
                status,
                price,
                time_id
              }}
            />
          ))}
        </div>
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
