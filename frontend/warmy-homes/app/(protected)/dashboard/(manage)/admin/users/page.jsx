"use client";
import UsersCard from "@/components/admin/admin-users-card/admin-users-card";
import styles from "./admin-users.module.scss";
import { useState } from "react";

export default function AdminUsers() {
  const [searchTerm, setSearchTerm] = useState("");
  const Users = [
    { id: 1, name: "Aaron Hank", email: "example@example.com", phone: "(454)454 45 45" },
    { id: 2, name: "Aaron Hank", email: "example@example.com", phone: "(454)454 45 45" },
    { id: 3, name: "Aaron Hank", email: "example@example.com", phone: "(454)454 45 45" },
    { id: 4, name: "Aaron Hank", email: "example@example.com", phone: "(454)454 45 45" },
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
      </div>
      <div className={styles.header}>
        <div>Name</div>
        <div>Email</div>
        <div>Phone</div>
        <div>Actions</div>
      </div>
      <div className={styles.cards}>
        {Users.map(({ id, name, email, phone }) => (
          <UsersCard
            key={id}
            name={name}
            email={email}
            phone={phone}
          />
        ))}
      </div>
    </div>
  );
}
