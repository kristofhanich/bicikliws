import * as React from "react";
import { Theme, createStyles, withStyles, WithStyles } from "@material-ui/core"
import withRoot from "./../../withRoot";
import { BicycleResponse } from "../../services/client/bicycleService";
import { WebAPI } from "../../services/webAPI";
import BicycleComponent from "../../components/bicycle";
import { CustomColors } from "../../style/colors";
import HeaderComponent from "../header/header";
import FooterComponent from "../footer/footer";
import { Route, RouteComponentProps } from "react-router";
import { Connected } from "./../../lib/store/connected.mixin";
import { AppStore } from "./../../lib/appStore";

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
    productContainer:
    {
      display: "flex",
      flexDirection: "row",
      flexGrow: 1,
      flexWrap: "wrap",
      justifyContent: "center",
      alignItems: "center"
    },
    hText:
    {
      color: "#33ff00"
    }
  });

interface IState
{
  bicycles: BicycleResponse[];
}

interface IProps
{}

  class Products extends Connected<typeof React.Component, IProps & WithStyles<typeof styles> & RouteComponentProps<{}>, IState, AppStore>(React.Component)
  {
    constructor(props: IProps & WithStyles<typeof styles>)
    {
      super(props);

      this.state =
      {
        bicycles: []
      }
    }

    componentWillMount = async (): Promise<void> =>
    {
      const bicyclesDB: BicycleResponse[] = await WebAPI.Bicycle.allData().then(x => x);

      this.setState
        ({
          ...this.state,
          bicycles: bicyclesDB
        });
    }

    render()
    {
        const css = this.props.classes;
        const bicycles:JSX.Element[] = this.state.bicycles.map
        (
          x => <Route key={x.Id} render={ props => <BicycleComponent bicycle={x} {...props}/> }/>
        );

        const Body = () =>
        <div className={css.container}>
        <h1 className={css.hText}>Termékek</h1>
        <Route render={ props => <HeaderComponent {...props}/> }/>
        <div className={css.productContainer}>
            {bicycles}
        </div>
        <FooterComponent />
        </div>

        return Body();
    }
  }

const ProductsPage = withRoot(withStyles(styles)(Products));
export default ProductsPage;