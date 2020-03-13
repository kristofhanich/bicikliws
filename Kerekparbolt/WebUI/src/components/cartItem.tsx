import * as React from "react";
import { Theme, createStyles, withStyles, WithStyles, withWidth } from "@material-ui/core"
import withRoot from "../withRoot";
import { BicycleResponse } from "../services/client/bicycleService";
import { Connected } from "../lib/store/connected.mixin";
import { RouteComponentProps, Route } from "react-router";
import { AppStore } from "../lib/appStore";
import { Routes } from "../routing/urls";
import { CustomColors } from "../style/colors";

const styles = (theme: Theme) =>
  createStyles
  ({
    container:
    {
      display: "flex",
      flexDirection: "column",
      flexGrow: 1,
      height: 30,
      width: "100%"
    },
    item:
    {
      display: "flex",
      border: "3px solid #33ff00",
      justifyContent: "center",
      borderRadius: 15,
      color: "#33ff00",
      padding: 10
    },
    img:
    {
      border: "1px solid #000",
      borderRadius: 10,
      height: 100,
      width: 100
    },
    pad:
    {
      padding: 10
    }
  });

interface IState
{}

interface IProps
{
    bicycle: BicycleResponse;
}

class CartItem extends Connected<typeof React.Component, IProps & WithStyles<typeof styles> & RouteComponentProps<{}>, IState, AppStore>(React.Component)
{
  constructor(props: IProps & WithStyles<typeof styles> & RouteComponentProps<{}>)
  {
    super(props);
  }

  render()
  {
      const css = this.props.classes;

      const Body = () =>
          <div className={css.container}>
              <div className={css.item}>
                <img className={css.img} src={this.props.bicycle.URL}/>
                <div className={css.pad}>
                  Cikkszám: {this.props.bicycle.Cikkszam}<br/>
                  Márka: {this.props.bicycle.Marka}<br/>
                  Típus: {this.props.bicycle.Tipus}<br/>
                  Váltó: {this.props.bicycle.Valto}<br/>
                  Vázméret: {this.props.bicycle.Vazmeret}<br/>
                  Ár: {this.props.bicycle.Ar}
                </div>
              </div>
          </div>

      return Body();
  }
}

const CartItemComponent = withRoot(withStyles(styles)(CartItem));
export default CartItemComponent;