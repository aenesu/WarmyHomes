import styles from "./price-tag.module.scss";

const PriceTag = ({ price }) => {
    return (
        <button className={styles.button}>
            $ {price}
        </button>
    );
};

export default PriceTag;