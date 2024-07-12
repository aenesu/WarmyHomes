// page.jsx
import React from 'react';
import MyFavoritesCard from "@/components/user-panel/my-favorites-card/my-favorites-card";
import Banner from "@/components/common/banner/banner";
import styles from "./tour-request.module.scss";

const MyTourRequest = () => {
    const myAdverts = [
        {id: 1, title: "Equestrian Family Home", country_id:"USA", city_id:"CA", district_id:"California City", price: 1400, category_id:"Villa", advert_type_id:"For Sale"},
        {id: 1, title: "Equestrian Family Home", country_id:"USA", city_id:"CA", district_id:"California City", price: 1400, category_id:"Villa", advert_type_id:"For Sale"},
        {id: 1, title: "Equestrian Family Home", country_id:"USA", city_id:"CA", district_id:"California City", price: 1400, category_id:"Villa", advert_type_id:"For Sale"}    
    ];

    return (
        <div className={styles.container}>
            <Banner title="MY TOUR REQUEST" />
            <div className={styles.words}>
                <h4>Property</h4>
                <h4>Category</h4>
                <h4>Type</h4>
                <h4>Action</h4>
            </div>
            <div className={styles.cards}>
                {myAdverts.map(({id, title, country_id, city_id, district_id, category_id, advert_type_id, price }) => (
                    <MyFavoritesCard key={id} {...{title, country_id, city_id, district_id, category_id, advert_type_id, price }} />
                ))}
            </div>
        </div>
    );
};

export default MyTourRequest;
