"use client";
import { useState } from "react";
import AdvertTypesCard from "@/components/admin/adverts-types-card/adverts-types-card";
import styles from "./adverts-types.module.scss";

export default function AdminAdvertTypes() {
  const [searchTerm, setSearchTerm] = useState("");
  const [advertTypes, setAdvertTypes] = useState([
    { id: 1, title: "For Sale" },
    { id: 2, title: "For Rent" },
  ]);

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
  };

  const filteredAdvertTypes = advertTypes.filter((advertType) =>
    advertType.title.toLowerCase().includes(searchTerm.toLowerCase())
  );

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
        <div className={styles.headerTitle}>Title</div>
        <div className={styles.headerAction}>Action</div>
      </div>
      <div className={styles.cards}>
        {filteredAdvertTypes.map(({ id, title }) => (
          <AdvertTypesCard key={id} title={title} />
        ))}
      </div>
    </div>
  );
}
