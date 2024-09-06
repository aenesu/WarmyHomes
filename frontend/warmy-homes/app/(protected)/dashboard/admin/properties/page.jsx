"use client"
import AdvertsCard from "@/components/admin/adverts-card/adverts-card"
import styles from "./adverts.module.scss"
import { useState } from "react";

export default function Properties() {
  const [formData, setFormData] = useState({
    search: "",
    category: "",
    type: "",
    status: ""
  });

  const Properties = [
    {id: 1, title: "Equestrian Family Home", city_id:"CA City", price: 1400, create_at:"03/04/2024", status:"Pending", view_count:"125"},
    {id: 2, title: "Luxury Condo", city_id:"NY City", price: 2500, create_at:"05/06/2024", status:"Active", view_count:"100"},
    {id: 3, title: "Modern Apartment", city_id:"LA City", price: 1800, create_at:"10/09/2024", status:"Sold", view_count:"250"},
    {id: 4, title: "Modern Apartment", city_id:"LA City", price: 1800, create_at:"10/09/2024", status:"Sold", view_count:"250"}
  ];

  // Pagination state
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 3; // Define how many items to show per page
  const totalPages = Math.ceil(Properties.length / itemsPerPage);
  const startIndex = (currentPage - 1) * itemsPerPage;
  const paginatedProperties = Properties.slice(startIndex, startIndex + itemsPerPage);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevData => ({
      ...prevData,
      [name]: value
    }));
  };

  const handleSearch = (e) => {
    e.preventDefault();
    // Implement your search logic here
    console.log('Searching with formData:', formData);
  };

  // Pagination handler
  const handlePageChange = (direction) => {
    setCurrentPage(prevPage => prevPage + direction);
  };

  const categories = ["Residential", "Commercial"];
  const types = ["House", "Apartment", "Condo"];
  const statuses = ["Active", "Pending", "Sold"];
  
  return (
    <div className={styles.container}>
      <form className={styles.searchForm} onSubmit={handleSearch}>
        <input
          type="text"
          name="search"
          placeholder="Enter your text here"
          value={formData.search}
          onChange={handleChange}
          className={styles.inputField}
        />
        <div className={styles.selectGroup}>
          <select
            name="category"
            value={formData.category}
            onChange={handleChange}
            className={styles.selectField}
          >
            <option value="">Category</option>
            {categories.map((category) => (
              <option key={category} value={category.toLowerCase()}>
                {category}
              </option>
            ))}
          </select>
          <select
            name="type"
            value={formData.type}
            onChange={handleChange}
            className={styles.selectField}
          >
            <option value="">Type</option>
            {types.map((type) => (
              <option key={type} value={type.toLowerCase()}>
                {type}
              </option>
            ))}
          </select>
          <select
            name="status"
            value={formData.status}
            onChange={handleChange}
            className={styles.selectField}
          >
            <option value="">Status</option>
            {statuses.map((status) => (
              <option key={status} value={status.toLowerCase()}>
                {status}
              </option>
            ))}
          </select>
          <button type="submit" className={styles.searchButton}>
            SEARCH
          </button>
        </div>
      </form>
      
      <div className={styles.words}> 
        <h4>Property</h4>
        <h4>Date Published</h4>
        <h4>Status</h4>
        <h4>View/Like/Tour</h4>
        <h4>Action</h4>
      </div>

      <div className={styles.cards}>
        {paginatedProperties.map(({id, title, city_id, location, price, create_at, status, view_count }) => (
          <AdvertsCard key={id} {...{title, city_id, location, price, create_at, status, view_count}}/>
        ))}
      </div>

      {/* Pagination controls */}
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
  );
}
