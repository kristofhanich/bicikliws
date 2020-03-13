import * as React from "react";
import { RouteComponentProps } from "react-router";
import { Theme, createStyles, withStyles, WithStyles } from "@material-ui/core"
import withRoot from "./../../withRoot";
import { StorageService } from "./../../services/client/storage.service";
import { StorageKeys } from "./../../settings/constats";
import { Routes, Urls } from "./../../routing/urls";
import { Connected } from "./../../lib/store/connected.mixin";
import { AppStore } from "./../../lib/appStore";
import { url } from "inspector";

const styles = (theme: Theme) =>
  createStyles
  ({
    container:
    {
      display: "flex",
      flexGrow: 1,
      minHeight: 50,
      alignItems: "center",
      color: "orange",
      width: "100%"
    },
    navbar:
    {
        padding: 5,
        margin: 0,
        width: "100%"
    },
    nav:
    {
        listStyleType: "none",
        margin: 0,
        padding: 0,
        overflow: "hidden",
        backgroundColor: "#121212",
    },
    navItem:
    {
        float: "left"
    },
    navItemText:
    {
        display: "block",
        color: "white",
        textAlign: "center",
        padding: "14px 16px",
        textDecoration: "none"
    },
    navItemLink:
    {
        display: "block",
        color: "white",
        textAlign: "center",
        padding: "14px 16px",
        textDecoration: "none",

        "&:hover":
        {
            backgroundColor: "#33ff00"
        }
    },
    cart:
    {
        float: "right",
    }
  });

interface IState
{
    isLoggedIn: boolean;
    loginStateText: string;
    cartCount: number
}

interface IProps
{}

  class Header extends Connected<typeof React.Component, IProps & WithStyles<typeof styles> & RouteComponentProps<{}>, IState, AppStore>(React.Component)
  {
    constructor(props: IProps & WithStyles<typeof styles> & RouteComponentProps<{}>)
    {
        super(props);

        this.state =
        {
            isLoggedIn: true,
            loginStateText : "KIJELENTKEZÉS",
            cartCount : this.store.state.cart.content().length
        }
    }

    componentDidMount = () =>
    {
        this.store.state.cart.count$.subscribe((data) =>
        {
            this.setState
            ({
                ...this.state,
                cartCount : data
            });
        });
    }

    logoutClickHandler = (): void =>
    {
        const storage: StorageService = new StorageService();
        storage.remove(StorageKeys.JWT);

        this.props.history.push(Urls.home);
    }

    productsClickHandler = (): void =>
    {
        this.props.history.push(Urls.products);
    }
    cartClickHandler = (): void =>
    {
        this.props.history.push(Urls.cart);
    }

    adminClickHandler = (): void =>
    {
        this.props.history.push(Urls.addProduct);
    }

    render()
    {
        const css = this.props.classes;
        const cartCount = this.state.cartCount;

        const Body = () =>
            <div className={css.container}>
                <div className={css.navbar}>
                    <ul className={css.nav}>
                        <li className={css.navItem}>
                            <span className={css.navItemText}>Bringa Webshop</span>
                        </li>
                        <li className={css.navItem}>
                            <span className={css.navItemLink} onClick={this.logoutClickHandler}>
                                {this.state.loginStateText}
                            </span>
                        </li>
                        <li className={css.navItem}>
                        <span className={css.navItemLink} onClick={this.productsClickHandler}>Termékek</span>
                        </li>
                        <li className={css.navItem}>
                            <span className={css.navItemLink} onClick={this.adminClickHandler}>Adminisztrátor felület</span>
                        </li>
                        <li className={css.cart}>
                            <span className={css.navItemLink} onClick={this.cartClickHandler}>
                                Kosár tartalma:{cartCount} kerékpár.
                            </span>
                        </li>
                    </ul>
                </div>
            </div>

        return Body();
    }
  }

const HeaderComponent = withRoot(withStyles(styles)(Header));
export default HeaderComponent;