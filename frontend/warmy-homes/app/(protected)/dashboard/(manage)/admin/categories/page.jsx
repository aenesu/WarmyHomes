"use client"
import CategoriesCard from "@/components/admin/categories-card/categories-card";
import styles from "./categories.module.scss";
import { useState } from "react";

export default function AdminCategories() {
  const [searchTerm, setSearchTerm] = useState("");
  const Categories = [
    { id: 1, icon: "house.svg", name: "Villa", sequence: 15, active: true },
    { id: 2, icon: "house.svg", name: "Villa", sequence: 15, active: true },
    { id: 3, icon: "house.svg", name: "Villa", sequence: 15, active: true },
    { id: 4, icon: "house.svg", name: "Villa", sequence: 15, active: true },
  ];

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
  };


  return (
    <div className={styles.container}>
      <div className={styles.searchBar}>
        <input
          type="text"
          placeholder="Type something"
          value={searchTerm}
          onChange={handleSearchChange}
          className={styles.searchInput}
        />
        <button className={styles.searchButton}>
          <img src="/assets/vectors/magnify.svg" alt="Search" />
        </button>
        <button className={styles.addButton}>ADD NEW</button>
      </div>
      <div className={styles.header}>
        <div>Icon</div>
        <div>Name</div>
        <div>Sequence</div>
        <div>Active</div>
        <div>Action</div>
      </div>
      <div className={styles.cards}>
        {Categories.map(({ id, icon, name, sequence, active }) => (
          <CategoriesCard
            key={id}
            icon={icon}
            name={name}
            sequence={sequence}
            active={active}
          />
        ))}
      </div>
    </div>
  );
}
