import MyAdvertsCard from '@/components/user-panel/my-adverts-card/my-adverts-card'
import React from 'react'
import styles from "./my-adverts.module.scss"

export default function myAdverts() {
    const myAdverts = [
        {id: 1, title: "Equestrian Family Home", city_id:"CA City", price: 1400, create_at:"01/06/2024", status:"Pending", view_count:"125"},
        {id: 1, title: "Equestrian Family Home", city_id:"CA City", price: 1400, create_at:"01/06/2024", status:"Pending", view_count:"125"},
        {id: 1, title: "Equestrian Family Home", city_id:"CA City", price: 1400, create_at:"01/06/2024", status:"Pending", view_count:"125"}
    ]
  return (
    <div className={styles.container} >
      <div className={styles.words}> 
        <h4>Property</h4>
        <h4>Date Published</h4>
        <h4>Status</h4>
        <h4>View/Like/Tour</h4>
        <h4>Action</h4>
      </div>
<div className={styles.cards}>
        {myAdverts.map(({id, title, city_id, location, price, create_at, status, view_count }) => (
          <MyAdvertsCard key={id} {...{title, city_id, location, price, create_at, status, view_count}}/>
        ))}
        </div>
    </div>
  )
}
