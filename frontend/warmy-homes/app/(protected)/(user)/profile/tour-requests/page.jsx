"use client";
import Banner from "@/components/common/banner/banner";
import styles from "./tour-request.module.scss";
import MyTourRequestCard from "@/components/user-panel/my-tour-request-card/my-tour-card";
import { useState } from "react";

const MyTourRequest = () => {
  // Sample data for tour requests
  const myAdverts = [
    { id: 1, title: "Equestrian Family Home", country_id: "USA", city_id: "CA", district_id: "California City", price: 1400, category_id: "John Doe", status: "Pending", advert_type_id: "10/10/2023", time_id: "10:30 PM" },
    { id: 2, title: "Cozy Cottage", country_id: "USA", city_id: "NY", district_id: "New York City", price: 2000, category_id: "Jane Smith", status: "Confirmed", advert_type_id: "12/10/2023", time_id: "2:00 PM" },
    { id: 3, title: "Cozy Cottage", country_id: "USA", city_id: "NY", district_id: "New York City", price: 2000, category_id: "Jane Smith", status: "Confirmed", advert_type_id: "12/10/2023", time_id: "2:00 PM" },
  ];

  // Pagination state
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 2; // Define how many items to show per page
  const totalPages = Math.ceil(myAdverts.length / itemsPerPage);
  const startIndex = (currentPage - 1) * itemsPerPage;
  const paginatedAdverts = myAdverts.slice(startIndex, startIndex + itemsPerPage);

  // Page change handler
  const handlePageChange = (direction) => {
    setCurrentPage((prevPage) => prevPage + direction);
  };

  return (
    <div className={styles.mainContainer}>
      <div className={styles.container}>
        <Banner title="MY TOUR REQUESTS" />

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
              {...{ title, country_id, city_id, district_id, category_id, advert_type_id, status, price, time_id }}
            />
          ))}
        </div>

        {/* Pagination controls with arrows */}
        <div className={styles.pagination}>
          <button
            onClick={() => handlePageChange(-1)}
            disabled={currentPage === 1}
            className={`${styles.pageButton} ${currentPage === 1 ? styles.disabled : ''}`}
          >
            <img src="/assets/vectors/arrowL.svg" alt="Previous" />
          </button>
          <span className={styles.pageInfo}>
             {currentPage} / {totalPages}
          </span>
          <button
            onClick={() => handlePageChange(1)}
            disabled={currentPage === totalPages}
            className={`${styles.pageButton} ${currentPage === totalPages ? styles.disabled : ''}`}
          >
            <img src="/assets/vectors/arrow.svg" alt="Next" />
          </button>
        </div>
      </div>
    </div>
  );
};

export default MyTourRequest;
