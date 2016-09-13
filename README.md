# Dagger 2 sub-component map binding example

This attempts to add sub component builders to an application via the `@Binds @IntoMap @ActivityKey(...)` method shown [here.](https://youtu.be/iwjXqRlEevg)

This is made possible by the new `@Module(subcomponents = {...})` mechanism in dagger 2.7

My attempt may be a bit generic heavy so suggestions on how to keep it clean and flexible are appreciated.