import * as React from "react";
import { Connected } from "./../lib/store/connected.mixin";
import { RouteComponentProps } from "react-router";
import { AppStore } from "./../lib/appStore";
import { Theme, createStyles, withStyles, WithStyles, TextField, Typography, Button } from "@material-ui/core"
import withRoot from "./../withRoot";
import { Routes } from "./../routing/urls";

const styles = (theme: Theme) =>
  createStyles
  ({})

interface IState
{}

interface IProps
{}


class Template extends Connected<typeof React.Component, IProps & WithStyles<typeof styles> & RouteComponentProps<{}>, IState, AppStore>(React.Component)
{
    constructor(props: IProps & WithStyles<typeof styles> & RouteComponentProps<{}>)
    {
        super(props);

        this.state =
        {}
    }

    render()
    {
        const css = this.props.classes;

        const Body = () =>
            <div></div>
        
        return Body();
    }
}

const TemplatePage = withRoot(withStyles(styles)(Template));
export default TemplatePage;