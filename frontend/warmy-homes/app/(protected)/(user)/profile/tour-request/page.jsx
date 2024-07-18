import MyFavoritesCard from "@/components/user-panel/my-favorites-card/my-favorites-card";
import Banner from "@/components/common/banner/banner";
import styles from "./tour-request.module.scss";

const MyTourRequest = () => {
    const myAdverts = [
        {id: 1, title: "Equestrian Family Home", country_id:"USA", city_id:"CA", district_id:"California City", price: 1400, category_id:"John Doe", advert_type_id:"10/10/2023", time_id: "10:30PM"},
        {id: 1, title: "Equestrian Family Home", country_id:"USA", city_id:"CA", district_id:"California City", price: 1400, category_id:"John Doe", advert_type_id:"10/10/2023", time_id: "10:30PM" }, 
    ];

    return (
        <div className={styles.mainContainer}>
        <div className={styles.container}>
            <Banner title="MY TOUR REQUEST" />
            <div className={styles.words}>
                <h4>Property</h4>
                <h4>Owner</h4>
                <h4>Status</h4>
                <h4>Tour Request Date</h4>
                <h4>Tour Request Time</h4>
                <h4>Action</h4>
            </div>
            <div className={styles.cards}>
                {myAdverts.map(({id, title, country_id, city_id, district_id, category_id, advert_type_id, price, time_id}) => (
                    <MyFavoritesCard key={id} {...{title, country_id, city_id, district_id, category_id, advert_type_id, price, time_id }} />
                ))}
            </div>
        </div>
        </div>
    );
};

export default MyTourRequest;