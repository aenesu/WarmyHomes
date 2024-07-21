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
      {id: 1, title: "Equestrian Family Home", city_id:"CA City", price: 1400, create_at:"03/04/2024", status:"Pending", view_count:"125"},
      {id: 1, title: "Equestrian Family Home", city_id:"CA City", price: 1400, create_at:"03/04/2024", status:"Pending", view_count:"125"}
  ];

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

const categories = ["Residential", "Commercial"];
const types = ["House", "Apartment", "Condo"];
const statuses = ["Active", "Pending", "Sold"];
  
return (
  <div className={styles.container}>
    <form className={styles.searchForm}>
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
      {Properties.map(({id, title, city_id, location, price, create_at, status, view_count }) => (
        <AdvertsCard key={id} {...{title, city_id, location, price, create_at, status, view_count}}/>
      ))}
      </div>
  </div>
)
}

