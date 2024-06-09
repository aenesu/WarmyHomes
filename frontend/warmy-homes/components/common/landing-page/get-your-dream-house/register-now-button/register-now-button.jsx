import Link from "next/link";
import styles from "./register-now-button.module.scss";

const RegisterNowButton = ({ className }) => {
    return (
        <Link href="/register" className={styles.link}>
            <button className={styles.button}>
                Register Now
                <img
                    src="/assets/vectors/right-upwards-arrow0.svg"
                    width={20}
                    height={20}
                    alt=""
                    style={{ marginLeft: "10px", width: "20px", height: "20px" }}
                />
            </button>
        </Link>
    );
};

export default RegisterNowButton;