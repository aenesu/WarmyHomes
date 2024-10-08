"use client";
import React, { useState, useEffect } from 'react';
import styles from './admin-tour-request.module.scss';
import AdminTourRequestCard from '@/components/admin/admin-tour-request-card/admin-tour-card';
import Pagination from '@/components/common/pagination/pagination';

export default function Properties() {
  const [formData, setFormData] = useState({
    search: '',
    category: '',
    type: '',
    status: ''
  });

  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 3;

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

  const handlePageChange = (page) => {
    if (page >= 1 && page <= totalPages) {
      setCurrentPage(page);
    }
  };

  const myAdverts = [
    { id: 1, title: 'Equestrian Family Home', country_id: 'USA', city_id: 'CA', district_id: 'California City', price: 1400, category_id: 'John Doe', guest_id: 'Michael Go', status: 'Pending', advert_type_id: '10/10/2023', time_id: '10:30 PM' },
    { id: 2, title: 'Equestrian Family Home', country_id: 'USA', city_id: 'CA', district_id: 'California City', price: 1400, category_id: 'John Doe', guest_id: 'Michael Go', status: 'Pending', advert_type_id: '10/10/2023', time_id: '10:30 PM' },
    { id: 3, title: 'Equestrian Family Home', country_id: 'USA', city_id: 'CA', district_id: 'California City', price: 1400, category_id: 'John Doe', guest_id: 'Michael Go', status: 'Pending', advert_type_id: '10/10/2023', time_id: '10:30 PM' },
    { id: 4, title: 'Equestrian Family Home', country_id: 'USA', city_id: 'CA', district_id: 'California City', price: 1400, category_id: 'John Doe', guest_id: 'Michael Go', status: 'Pending', advert_type_id: '10/10/2023', time_id: '10:30 PM' },
    { id: 5, title: 'Equestrian Family Home', country_id: 'USA', city_id: 'CA', district_id: 'California City', price: 1400, category_id: 'John Doe', guest_id: 'Michael Go', status: 'Pending', advert_type_id: '10/10/2023', time_id: '10:30 PM' },
    { id: 6, title: 'Equestrian Family Home', country_id: 'USA', city_id: 'CA', district_id: 'California City', price: 1400, category_id: 'John Doe', guest_id: 'Michael Go', status: 'Pending', advert_type_id: '10/10/2023', time_id: '10:30 PM' },
    { id: 7, title: 'Equestrian Family Home', country_id: 'USA', city_id: 'CA', district_id: 'California City', price: 1400, category_id: 'John Doe', guest_id: 'Michael Go', status: 'Pending', advert_type_id: '10/10/2023', time_id: '10:30 PM' },
    { id: 8, title: 'Equestrian Family Home', country_id: 'USA', city_id: 'CA', district_id: 'California City', price: 1400, category_id: 'John Doe', guest_id: 'Michael Go', status: 'Pending', advert_type_id: '10/10/2023', time_id: '10:30 PM' }
  ];

  const totalPages = Math.ceil(myAdverts.length / itemsPerPage);
  const startIndex = (currentPage - 1) * itemsPerPage;
  const paginatedAdverts = myAdverts.slice(startIndex, startIndex + itemsPerPage);

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
          <h4>Guest</h4>
          <h4>Status</h4>
          <h4>Tour Date</h4>
          <h4>Tour Time</h4>
          <h4>Action</h4>
        </div>
        <div className={styles.cards}>
          {paginatedAdverts.map(({ id, title, country_id, city_id, district_id, category_id, guest_id, advert_type_id, price, status, time_id }) => (
            <AdminTourRequestCard
              key={id}
              {...{
                title,
                country_id,
                city_id,
                district_id,
                category_id,
                guest_id,
                advert_type_id,
                status,
                price,
                time_id,

              }}
            />
          ))}
        </div>
      </div>
      {/* Pagination Component */}
      <Pagination
        currentPage={currentPage}
        totalPages={totalPages}
        onPageChange={handlePageChange}
      />
    </div>
  );
}
