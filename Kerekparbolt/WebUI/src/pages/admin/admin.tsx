import * as React from "react";
import { Connected } from "./../../lib/store/connected.mixin";
import { RouteComponentProps, Route } from "react-router";
import { AppStore } from "./../../lib/appStore";
import { Theme, createStyles, withStyles, WithStyles, TextField, Typography, Button } from "@material-ui/core"
import withRoot from "./../../withRoot";
import { Routes } from "./../../routing/urls";
import HeaderComponent from "../header/header";
import FooterComponent from "../footer/footer";
import { CustomColors } from "./../../style/colors";

const styles = (theme: Theme) =>
  createStyles
  ({
    container:
    {
      display: "flex",
      flexDirection: "column",
      flexGrow: 1,
      justifyContent: "center",
      alignItems: "center",
      minHeight: "100vh",
      backgroundColor: CustomColors.background
    },
    hText:
    {
      color: "#33ff00"
    },
    action:
    {
        color: "#FFF",
        "&:hover":
        {
            backgroundColor: "#33ff00",
            color: "#000"
        }
    }
  })

interface IState
{}

interface IProps
{}


class Admin extends Connected<typeof React.Component, IProps & WithStyles<typeof styles> & RouteComponentProps<{}>, IState, AppStore>(React.Component)
{
    constructor(props: IProps & WithStyles<typeof styles> & RouteComponentProps<{}>)
    {
        super(props);

        this.state =
        {}
    }

    createClickHandler = (): void =>
    {
        this.props.history.push(Routes.AddProduct);
    }

    render()
    {
        const css = this.props.classes;

        const Body = () =>
            <div className={css.container}>
            <h1 className={css.hText}>Admin</h1>
            <Route render={ props => <HeaderComponent {...props}/> }/>
            <h3 className={css.action} onClick={this.createClickHandler}>Product Create</h3>
            <FooterComponent />
            </div>
        
        return Body();
    }
}

const AdminPage = withRoot(withStyles(styles)(Admin));
export default AdminPage;